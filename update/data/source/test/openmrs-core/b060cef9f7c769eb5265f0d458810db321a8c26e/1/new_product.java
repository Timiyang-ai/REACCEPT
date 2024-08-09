@Override
	public void validate(Object obj, Errors errors) {
		Alert alert = (Alert) obj;
		if (alert == null) {
			errors.rejectValue("alert", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "Alert.text.required");
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "text");
		}
	}