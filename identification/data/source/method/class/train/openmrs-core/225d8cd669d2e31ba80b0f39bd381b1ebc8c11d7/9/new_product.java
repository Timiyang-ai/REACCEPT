public void validate(Object obj, Errors errors) {
		ConceptDatatype cd = (ConceptDatatype) obj;
		if (cd == null) {
			errors.rejectValue("conceptDatatype", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
	}