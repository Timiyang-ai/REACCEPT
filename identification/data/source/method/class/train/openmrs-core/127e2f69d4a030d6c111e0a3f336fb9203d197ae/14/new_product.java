public void validate(Object obj, Errors errors) throws APIException {
		if (log.isDebugEnabled()) {
			log.debug(this.getClass().getName() + ".validate...");
		}
		
		if (obj == null || !(obj instanceof Encounter)) {
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type " + Encounter.class);
		}
		
		Encounter encounter = (Encounter) obj;
		
		ValidationUtils.rejectIfEmpty(errors, "patient", "Encounter.error.patient.required", "Patient is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "encounterDatetime", "error.null");
		if (encounter.getVisit() != null && !ObjectUtils.equals(encounter.getVisit().getPatient(), encounter.getPatient())) {
			errors.rejectValue("visit", "Encounter.visit.patients.dontMatch",
			    "The patient for the encounter and visit should be the same");
		}
		
		Date encounterDateTime = encounter.getEncounterDatetime();
		
		if (encounterDateTime != null && encounterDateTime.after(new Date())) {
			errors.rejectValue("encounterDatetime", "Encounter.datetimeShouldBeBeforeCurrent",
			    "The encounter datetime should be before the current date.");
		}
		
		Visit visit = encounter.getVisit();
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
	}