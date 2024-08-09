public void validate(Object obj, Errors errors) {
		VisitAttributeType visitAttributeType = (VisitAttributeType) obj;
		if (visitAttributeType == null) {
			errors.rejectValue("visitAttributeType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minOccurs", "error.null");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxOccurs", "error.null");
		}
	}