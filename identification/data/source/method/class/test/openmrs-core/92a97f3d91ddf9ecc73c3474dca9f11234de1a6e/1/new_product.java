public void validate(Object obj, Errors errors) {
		User user = (User)obj;
		if (user == null) {
			errors.rejectValue("user", "error.general");
		}
		else {
			if (user.getUserId() != null)
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "systemId", "error.systemId");
			
			// TODO validate username checkdigit here
			if (user.isVoided() && user.getVoidReason().trim().equals(""))
				errors.rejectValue("voidReason", "error.null");
		}
	}