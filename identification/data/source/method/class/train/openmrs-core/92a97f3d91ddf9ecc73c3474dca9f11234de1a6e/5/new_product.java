public void validate(Object obj, Errors errors) {
		User user = (User)obj;
		if (user == null) {
			errors.rejectValue("user", "error.general");
		}
		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username");
			// TODO validate username checkdigit here
			if (user.isVoided() && user.getVoidReason().trim().equals(""))
				errors.rejectValue("voidReason", "error.null");
		}
	}