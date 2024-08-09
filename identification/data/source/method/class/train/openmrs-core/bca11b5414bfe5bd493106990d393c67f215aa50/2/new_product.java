@Override
	public void validate(Object target, Errors errors) {
		if (target != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "LocationTag.error.name.required");
		}
	}