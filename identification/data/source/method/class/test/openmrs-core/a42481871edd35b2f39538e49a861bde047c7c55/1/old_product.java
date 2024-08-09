public void validate(Object obj, Errors errors) {
		Form form = (Form)obj;
		if (form == null) {
			errors.rejectValue("form", "error.general");
		}
		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
		}
	}