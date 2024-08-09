public void validate(Object obj, Errors errors) {
		User user = (User)obj;
		if (user == null) {
			errors.rejectValue("user", "error.general");
		}
		else {
			if (user.getUsername() == null || user.getUsername() == "") {
				errors.rejectValue("username", "error.username");
			}
			// TODO check username check digit here
		}
	}