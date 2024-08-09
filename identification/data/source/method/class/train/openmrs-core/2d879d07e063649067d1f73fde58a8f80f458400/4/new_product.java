@Override
	public void beforeCreateEncounter(Encounter encounter) {
		
		//Do nothing if the encounter already belongs to a visit.
		if (encounter.getVisit() != null)
			return;
		
		List<Patient> patients = new ArrayList<Patient>();
		patients.add(encounter.getPatient());
		List<Visit> visits = Context.getVisitService().getVisits(/*visitTypes*/null, patients,
		/*locations*/null, /*indications*/null, /*minStartDatetime*/null,
		/*maxStartDatetime*/encounter.getEncounterDatetime(),
		/*minEndDatetime*/null, /*maxEndDatetime*/null, /*attributeValues*/null, /*includeInactive*/true, /*includeVoided*/
		false);
		
		if (visits == null)
			return;
		
		Date encounterDate = encounter.getEncounterDatetime();
		
		for (Visit visit : visits) {
			//skip visits which are started after the encounter date.
			if (visit.getStartDatetime().after(encounterDate)) {
				continue;
			}
			
			if (visit.getLocation() == null || Location.isInHierarchy(encounter.getLocation(), visit.getLocation())) {
				encounter.setVisit(visit);
				return;
			}
		}
	}