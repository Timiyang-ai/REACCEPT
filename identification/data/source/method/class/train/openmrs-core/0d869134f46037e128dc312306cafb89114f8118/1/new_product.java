public Encounter saveEncounter(Encounter encounter) throws APIException {
		
		//If new encounter, try to assign a visit using the registered visit assignment handler.
		if (encounter.getEncounterId() == null) {
			
			//Am using Context.getEncounterService().getActiveEncounterVisitHandler() instead of just
			//getActiveEncounterVisitHandler() for modules which may want to AOP around this call.
			EncounterVisitHandler encounterVisitHandler = Context.getEncounterService().getActiveEncounterVisitHandler();
			if (encounterVisitHandler != null) {
				encounterVisitHandler.beforeCreateEncounter(encounter);
				
				//If we have been assigned a new visit, persist it.
				if (encounter.getVisit() != null && encounter.getVisit().getVisitId() == null) {
					Context.getVisitService().saveVisit(encounter.getVisit());
				}
			}
		}
		
		Errors errors = new BindException(encounter, "encounter");
		
		boolean isNewEncounter = false;
		Date newDate = encounter.getEncounterDatetime();
		Date originalDate = null;
		Location newLocation = encounter.getLocation();
		Location originalLocation = null;
		// check permissions
		if (encounter.getEncounterId() == null) {
			isNewEncounter = true;
			Context.requirePrivilege(PrivilegeConstants.ADD_ENCOUNTERS);
		} else {
			Context.requirePrivilege(PrivilegeConstants.EDIT_ENCOUNTERS);
		}
		
		// This must be done after setting dateCreated etc on the obs because
		// of the way the ORM tools flush things and check for nullity
		// This also must be done before the save encounter so we can use the
		// orig date
		// after the save
		Patient p = encounter.getPatient();
		
		if (!isNewEncounter) {
			// fetch the datetime from the database prior to saving for this
			// encounter
			// to see if it has changed and change all obs after saving if so
			originalDate = dao.getSavedEncounterDatetime(encounter);
			if (encounter.getLocation() != null)
				originalLocation = dao.getSavedEncounterLocation(encounter);
			// Our data model duplicates the patient column to allow for
			// observations to
			// not have to look up the parent Encounter to find the patient
			// Therefore, encounter.patient must always equal
			// encounter.observations[0-n].patient
			
			// If we are changing encounter.encounterDatetime, then we need to
			// also apply that
			// to Obs that inherited their obsDatetime from the encounter in the
			// first place
			
			for (Obs obs : encounter.getAllObs(true)) {
				// if the date was changed
				if (OpenmrsUtil.compare(originalDate, newDate) != 0) {
					
					// if the obs datetime is the same as the
					// original encounter datetime, fix it
					if (OpenmrsUtil.compare(obs.getObsDatetime(), originalDate) == 0) {
						obs.setObsDatetime(newDate);
					}
					
				}
				
				if (!OpenmrsUtil.nullSafeEquals(newLocation, originalLocation)) {
					if (obs.getLocation().equals(originalLocation)) {
						obs.setLocation(newLocation);
					}
				}
				
				// if the Person in the obs doesn't match the Patient in the
				// encounter, fix it
				if (!obs.getPerson().getPersonId().equals(p.getPatientId())) {
					obs.setPerson(p);
				}
			}
		}
		// same goes for Orders
		for (Order o : encounter.getOrders()) {
			if (!p.equals(o.getPatient())) {
				o.setPatient(p);
			}
		}
		
		// do the actual saving to the database
		dao.saveEncounter(encounter);
		
		// save the new orders
		for (Order o : encounter.getOrders()) {
			if (o.getOrderId() == null) {
				Context.getOrderService().saveOrder(o, null);
			}
		}
		return encounter;
	}