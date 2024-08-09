@Override
	public void beforeCreateEncounter(Encounter encounter) {
		
		//Do nothing if the encounter already belongs to a visit.
		if (encounter.getVisit() != null) {
			return;
		}
		
		List<Patient> patients = new ArrayList<>();
		patients.add(encounter.getPatient());
		
		//Fetch visits for this patient that haven't ended by the encounter date.
		List<Visit> visits = Context.getVisitService().getVisits(null, patients, null, null, null,
		    encounter.getEncounterDatetime(), null, null, null, true, false);
		
		if (visits == null) {
			return;
		}
		
		Date encounterDate = encounter.getEncounterDatetime();
		
		for (Visit visit : visits) {
			//skip visits which are started after the encounter date.
			if (visit.getStartDatetime().after(encounterDate)) {
				continue;
			}
			
			//skip visits which ended before the encounter date
			if (visit.getStopDatetime() != null && visit.getStopDatetime().before(encounterDate)) {
				continue;
			}
			
			if (visit.getLocation() == null || Location.isInHierarchy(encounter.getLocation(), visit.getLocation())) {
				encounter.setVisit(visit);
				return;
			}
		}
	}