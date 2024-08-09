public void validate(Object obj, Errors errors) {
		Role role = (Role)obj;
		if (role == null) {
			errors.rejectValue("role", "error.general");
		}
		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "error.role");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
	}