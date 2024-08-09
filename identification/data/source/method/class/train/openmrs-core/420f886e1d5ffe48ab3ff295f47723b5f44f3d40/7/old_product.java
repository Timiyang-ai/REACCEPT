public void validate(Object obj, Errors errors) {
		PatientIdentifierType identifierType = (PatientIdentifierType) obj;
		if (identifierType == null) {
			errors.rejectValue("identifierType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
	}