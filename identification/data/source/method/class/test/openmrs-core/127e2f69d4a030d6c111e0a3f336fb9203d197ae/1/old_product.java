public void validate(Object obj, Errors errors) throws APIException {
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ".validate...");
		
		if (obj == null || !(obj instanceof Encounter))
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type" + Encounter.class);
		
		Encounter encounter = (Encounter) obj;
		
		if (encounter != null) {
			if (encounter.getVisit() != null && !encounter.getVisit().getPatient().equals(encounter.getPatient())) {
				errors.rejectValue("visit", "Encounter.visit.patients.dontMatch",
				    "The patient for the encounter and visit should be the same");
			}
		}
	}