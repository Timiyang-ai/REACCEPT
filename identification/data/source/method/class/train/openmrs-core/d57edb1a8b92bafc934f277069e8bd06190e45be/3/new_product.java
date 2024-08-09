@Override
	public void beforeCreateEncounter(Encounter encounter) {
		
		//Do the default assignment to an existing visit.
		super.beforeCreateEncounter(encounter);
		
		//Do nothing if the encounter already belongs to a visit.
		if (encounter.getVisit() != null)
			return;
		
		Visit visit = new Visit();
		visit.setStartDatetime(encounter.getEncounterDatetime());
		visit.setLocation(encounter.getLocation());
		visit.setPatient(encounter.getPatient());
		
		visit.setVisitType(getVisitType(encounter.getEncounterType()));
		
		//set stop date time to last millisecond of the encounter day.
		visit.setStopDatetime(OpenmrsUtil.getLastMomentOfDay(encounter.getEncounterDatetime()));
		
		encounter.setVisit(visit);
	}