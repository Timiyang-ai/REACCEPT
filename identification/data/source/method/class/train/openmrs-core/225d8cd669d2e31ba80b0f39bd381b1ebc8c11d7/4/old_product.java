public void validate(Object o, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
	}