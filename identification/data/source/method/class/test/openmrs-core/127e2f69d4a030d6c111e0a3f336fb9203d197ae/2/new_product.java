public void validate(Object obj, Errors errors) throws APIException {
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ".validate...");
		
		if (obj == null || !(obj instanceof Encounter))
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type " + Encounter.class);
		
		Encounter encounter = (Encounter) obj;
		
		if (encounter != null) {
			ValidationUtils.rejectIfEmpty(errors, "patient", "Encounter.error.patient.required", "Patient is required");
			if (encounter.getVisit() != null && !encounter.getVisit().getPatient().equals(encounter.getPatient())) {
				errors.rejectValue("visit", "Encounter.visit.patients.dontMatch",
				    "The patient for the encounter and visit should be the same");
			}
			
			Visit visit = encounter.getVisit();
			Date encounterDateTime = encounter.getEncounterDatetime();
			if (visit != null && encounterDateTime != null) {
				if (visit.getStartDatetime() != null && encounterDateTime.before(visit.getStartDatetime())) {
					errors.rejectValue("encounterDatetime", "Encounter.datetimeShouldBeInVisitDatesRange",
					    "The encounter datetime should be between the visit start and stop dates.");
				}
				
				if (visit.getStopDatetime() != null && encounterDateTime.after(visit.getStopDatetime())) {
					errors.rejectValue("encounterDatetime", "Encounter.datetimeShouldBeInVisitDatesRange",
					    "The encounter datetime should be between the visit start and stop dates.");
				}
			}
			
			Map<Integer, EncounterRole> encounterMap = new HashMap<Integer, EncounterRole>();
			
			Map<EncounterRole, Set<Provider>> providers = encounter.getProvidersByRoles();
			for (Entry<EncounterRole, Set<Provider>> entry : providers.entrySet()) {
				EncounterRole encounterRole = entry.getKey();
				
				if (encounterMap.containsKey(encounterRole.getEncounterRoleId())) {
					errors.rejectValue("providersByRoles", "Encounter.error.duplicateProviderEncounterRole",
					    "Provider cannot be added more than once for the same encounter role");
					break;
				}
				
				encounterMap.put(encounterRole.getEncounterRoleId(), encounterRole);
			}
		}
	}