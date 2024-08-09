public void validate(Object obj, Errors errors) {
		Location location = (Location)obj;
		if (location == null) {
			errors.rejectValue("location", "error.general");
		}
		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
	}