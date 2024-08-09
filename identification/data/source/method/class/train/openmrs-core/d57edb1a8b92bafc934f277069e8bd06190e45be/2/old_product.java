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
		
		try {
			
			if (encounterVisitMapping == null) {
				// Create cache of mappings encounter type - visit type
				LoadingCache<EncounterType, VisitType> temp = CacheBuilder.newBuilder().build(
				    new CacheLoader<EncounterType, VisitType>() {
					    
					    public VisitType load(EncounterType key) throws APIException {
						    return loadVisitType(key);
					    }
				    });
				setEncounterVisitMapping(temp);
				Context.getAdministrationService().addGlobalPropertyListener(this);
			}
			
			VisitType visitType = encounterVisitMapping.get(encounter.getEncounterType());
			visit.setVisitType(visitType);
		}
		catch (ExecutionException e) {
			throw new APIException("Error getting mapping encounter type - visit type from cache", e);
		}
		
		//set stop date time to last millisecond of the encounter day.
		visit.setStopDatetime(OpenmrsUtil.getLastMomentOfDay(encounter.getEncounterDatetime()));
		
		encounter.setVisit(visit);
	}