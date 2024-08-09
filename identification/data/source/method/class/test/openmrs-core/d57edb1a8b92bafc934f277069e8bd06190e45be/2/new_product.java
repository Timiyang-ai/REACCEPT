@Override
	public void beforeCreateEncounter(Encounter encounter) {
		
		//Do the default assignment to an existing visit.
		super.beforeCreateEncounter(encounter);
		
		//Do nothing if the encounter already belongs to a visit.
		if (encounter.getVisit() != null) {
			return;
		}
		
		Visit visit = new Visit();
		visit.setStartDatetime(encounter.getEncounterDatetime());
		visit.setLocation(encounter.getLocation());
		visit.setPatient(encounter.getPatient());
		
		if (encounterVisitMapping == null) {
			//initial one-time setup
			setEncounterVisitMapping(new HashMap<EncounterType, VisitType>());
			Context.getAdministrationService().addGlobalPropertyListener(this);
		}
		
		VisitType visitType = encounterVisitMapping.get(encounter.getEncounterType());
		if (visitType == null) {
			visitType = loadVisitType(encounter.getEncounterType());
			
			//replace reference instead of synchronizing
			Map<EncounterType, VisitType> newMap = new HashMap<EncounterType, VisitType>(encounterVisitMapping);
			newMap.put(encounter.getEncounterType(), visitType);
			
			setEncounterVisitMapping(newMap);
		}
		visit.setVisitType(visitType);
		
		//set stop date time to last millisecond of the encounter day.
		visit.setStopDatetime(OpenmrsUtil.getLastMomentOfDay(encounter.getEncounterDatetime()));
		
		encounter.setVisit(visit);
	}