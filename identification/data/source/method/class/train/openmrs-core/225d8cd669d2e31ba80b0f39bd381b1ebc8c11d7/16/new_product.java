public void validate(Object obj, Errors errors) {
		HL7Source hl7Source = (HL7Source) obj;
		if (hl7Source == null) {
			errors.rejectValue("hl7Source", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
	}