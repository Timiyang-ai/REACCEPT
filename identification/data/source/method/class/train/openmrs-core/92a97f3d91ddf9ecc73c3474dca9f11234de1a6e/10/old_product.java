public void validate(Object obj, Errors errors) {
		User user = (User)obj;
		if (user == null) {
			errors.rejectValue("user", "general.error");
		}
		else {
			if (user.getUsername() == null || user.getUsername() == "") {
				errors.rejectValue("username", "user.username.error");
			}
			// TODO check username check digit here
		}
	}