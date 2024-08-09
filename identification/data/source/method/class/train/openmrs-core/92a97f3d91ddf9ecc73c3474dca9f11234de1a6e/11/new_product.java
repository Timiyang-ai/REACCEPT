public void validate(Object obj, Errors errors) {
		User user = (User)obj;
		if (user == null) {
			errors.rejectValue("user", "error.general");
		}
		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username");
			// TODO check username check digit here
		}
	}