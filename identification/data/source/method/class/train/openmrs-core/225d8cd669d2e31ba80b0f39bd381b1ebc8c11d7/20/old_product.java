public void validate(Object obj, Errors errors) throws IllegalArgumentException {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		if (obj == null || !(obj instanceof Drug))
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type" + Drug.class);
		log.debug("request to validate drug having concept: " + ((Drug) obj).getConcept());
		ValidationUtils.rejectIfEmpty(errors, "concept", "ConceptDrug.error.conceptRequired");
	}