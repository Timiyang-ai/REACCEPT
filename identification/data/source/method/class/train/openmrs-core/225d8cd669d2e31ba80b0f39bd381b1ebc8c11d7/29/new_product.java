public void validate(Object obj, Errors errors) {
		ConceptClass cc = (ConceptClass) obj;
		if (cc == null) {
			errors.rejectValue("conceptClass", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
	}