public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		if (user == null) {
			errors.rejectValue("user", "error.general");
		} else {
			if (user.isVoided() && user.getVoidReason().trim().equals(""))
				errors.rejectValue("voidReason", "error.null");
		}
	}