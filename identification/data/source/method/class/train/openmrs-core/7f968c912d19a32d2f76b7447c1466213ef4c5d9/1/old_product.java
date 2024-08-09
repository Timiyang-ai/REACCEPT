@Override
	public void beforeCreateEncounter(Encounter encounter) {
		
		//Do nothing if the encounter already belongs to a visit.
		if (encounter.getVisit() != null)
			return;
		
		List<Visit> visits = Context.getVisitService().getVisitsByPatient(encounter.getPatient(), true, false);
		if (visits == null)
			return;
		
		Date encounterDate = encounter.getEncounterDatetime();
		
		for (Visit visit : visits) {
			//skip visits which are started after the encounter date.
			if (visit.getStartDatetime().after(encounterDate)) {
				continue;
			}
			
			//skip visits which have ended before the encounter date.
			if (visit.getStopDatetime() != null && visit.getStopDatetime().before(encounterDate)) {
				continue;
			}
			
			if (visit.getLocation() == null || visit.getLocation().equals(encounter.getLocation())) {
				encounter.setVisit(visit);
				return;
			}
		}
	}