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
				if (encounterDateTime.before(visit.getStartDatetime())
				        || (visit.getStopDatetime() != null && encounterDateTime.after(visit.getStopDatetime()))) {
					errors.rejectValue("encounterDatetime", "Encounter.datetimeShouldBeInVisitDatesRange",
					    "The encounter datetime should be within the visit start and stop dates.");
				}
			}
		}
	}