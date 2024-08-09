@Override
	public void validate(Object target, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		if (target != null) {
			LocationTag locationTag = (LocationTag) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "LocationTag.error.name.required");
		}
	}