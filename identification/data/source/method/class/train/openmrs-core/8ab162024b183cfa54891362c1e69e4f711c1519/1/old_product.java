public Encounter saveEncounter(Encounter encounter) throws APIException {
		boolean isNewEncounter = false;
		Date newDate = encounter.getEncounterDatetime();
		Date originalDate = null;
		
		// check permissions
		if (encounter.getEncounterId() == null) {
			isNewEncounter = true;
			Context.requirePrivilege(OpenmrsConstants.PRIV_ADD_ENCOUNTERS);
		} else {
			Context.requirePrivilege(OpenmrsConstants.PRIV_EDIT_ENCOUNTERS);
		}
		
		// This must be done after setting dateCreated etc on the obs because
		// of the way the ORM tools flush things and check for nullity
		// This also must be done before the save encounter so we can use the orig date
		// after the save
		if (!isNewEncounter) {
			// fetch the datetime from the database prior to saving for this encounter
			// to see if it has changed and change all obs after saving if so
			originalDate = dao.getSavedEncounterDatetime(encounter);
			
			// Our data model duplicates the patient column to allow for observations to
			//   not have to look up the parent Encounter to find the patient
			// Therefore, encounter.patient must always equal encounter.observations[0-n].patient
			
			// If we are changing encounter.encounterDatetime, then we need to also apply that
			// to Obs that inherited their obsDatetime from the encounter in the first place
			
			Patient p = encounter.getPatient();
			for (Obs obs : encounter.getAllObs(true)) {
				// if the date was changed
				if (OpenmrsUtil.compare(originalDate, newDate) != 0) {
					
					// if the obs datetime is the same as the
					// original encounter datetime, fix it
					if (OpenmrsUtil.compare(obs.getObsDatetime(), originalDate) == 0) {
						obs.setObsDatetime(newDate);
					}
					
				}
				
				// if the Person in the obs doesn't match the Patient in the encounter, fix it
				if (!obs.getPerson().getPersonId().equals(p.getPatientId())) {
					obs.setPerson(p);
				}
				
			}
			
			// same goes for Orders
			for (Order o : encounter.getOrders()) {
				if (!p.equals(o.getPatient())) {
					o.setPatient(p);
				}
			}
		}
		
		// do the actual saving to the database
		dao.saveEncounter(encounter);
		
		return encounter;
	}