public void validate(Object obj, Errors errors) {
		VisitType visitType = (VisitType) obj;
		if (visitType == null) {
			errors.rejectValue("visitType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
		}
	}