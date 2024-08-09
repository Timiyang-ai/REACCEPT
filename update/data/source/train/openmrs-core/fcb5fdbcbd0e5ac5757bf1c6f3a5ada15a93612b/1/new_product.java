@Override
	public Encounter saveEncounter(Encounter encounter) throws APIException {
		
		// if authenticated user is not supposed to edit encounter of certain type
		failIfDeniedToEdit(encounter);
		
		//If new encounter, try to assign a visit using the registered visit assignment handler.
		createVisitForNewEncounter(encounter);
		
		// check permissions
		boolean isNewEncounter = requirePrivilege(encounter);
		
		// This must be done after setting dateCreated etc on the obs because
		// of the way the ORM tools flush things and check for nullity
		// This also must be done before the save encounter so we can use the
		// orig date
		// after the save
		Patient p = encounter.getPatient();
		Date originalDate;
		Location originalLocation = null;
		
		if (!isNewEncounter) {
			// fetch the datetime from the database prior to saving for this
			// encounter
			// to see if it has changed and change all obs after saving if so
			originalDate = dao.getSavedEncounterDatetime(encounter);
			if (encounter.getLocation() != null) {
				originalLocation = dao.getSavedEncounterLocation(encounter);
			}
			// Our data model duplicates the patient column to allow for
			// observations to
			// not have to look up the parent Encounter to find the patient
			// Therefore, encounter.patient must always equal
			// encounter.observations[0-n].patient
			
			// If we are changing encounter.encounterDatetime, then we need to
			// also apply that
			// to Obs that inherited their obsDatetime from the encounter in the
			// first place
			
			Date newDate = encounter.getEncounterDatetime();
			Location newLocation = encounter.getLocation();
			for (Obs obs : encounter.getAllFlattenedObs(true)) {
				// if the date was changed
				if (OpenmrsUtil.compare(originalDate, newDate) != 0
				        && OpenmrsUtil.compare(obs.getObsDatetime(), originalDate) == 0) {
					
					// if the obs datetime is the same as the
					// original encounter datetime, fix it
					obs.setObsDatetime(newDate);
					
				}
				
				if (!OpenmrsUtil.nullSafeEquals(newLocation, originalLocation) && obs.getLocation().equals(originalLocation)) {
					obs.setLocation(newLocation);
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

		// save the new orderGroups
		for (OrderGroup orderGroup : encounter.getOrderGroups()) {
			Context.getOrderService().saveOrderGroup(orderGroup);
		}
		//save the new orders which do not have order groups
		for (Order o : encounter.getOrdersWithoutOrderGroups()) {
			if (o.getOrderId() == null) {
				Context.getOrderService().saveOrder(o, null);
			}
		}
		
		// save the Obs
		String changeMessage = Context.getMessageSourceService().getMessage("Obs.void.reason.default");
		ObsService os = Context.getObsService();
		List<Obs> obsToRemove = new ArrayList<>();
		List<Obs> obsToAdd = new ArrayList<>();
		for (Obs o : encounter.getObsAtTopLevel(true)) {
			if (o.getId() == null) {
				os.saveObs(o, null);
			} else {
				Obs newObs = os.saveObs(o, changeMessage);
				//The logic in saveObs evicts the old obs instance, so we need to update the collection
				//with the newly loaded and voided instance, apparently reloading the encounter
				//didn't do the tick
				obsToRemove.add(o);
				obsToAdd.add(os.getObs(o.getId()));
				obsToAdd.add(newObs);
			}
		}

		removeGivenObsAndTheirGroupMembersFromEncounter(obsToRemove, encounter);
		addGivenObsAndTheirGroupMembersToEncounter(obsToAdd, encounter);
		return encounter;
	}