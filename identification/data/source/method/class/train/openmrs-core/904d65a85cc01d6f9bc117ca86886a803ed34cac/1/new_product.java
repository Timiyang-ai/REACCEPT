public void validate(Object obj, Errors errors) throws IllegalArgumentException {
		if (obj == null || !(obj instanceof ConceptSource)) {
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type "
			        + ConceptSource.class);
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.null");
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "name", "hl7Code", "uniqueId", "description",
			    "retireReason");
		}
		
	}