public void validate(Object obj, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		Privilege privilege = (Privilege) obj;
		if (privilege == null) {
			errors.rejectValue("privilege", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "privilege", "error.privilege");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
	}