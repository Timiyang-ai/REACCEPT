public void validate(Object obj, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		VisitType visitType = (VisitType) obj;
		if (visitType == null) {
			errors.rejectValue("visitType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
		}
	}