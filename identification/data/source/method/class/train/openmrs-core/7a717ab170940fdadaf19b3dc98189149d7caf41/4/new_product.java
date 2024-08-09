public void validate(Object obj, Errors errors) {
		Role role = (Role)obj;
		if (role == null) {
			errors.rejectValue("role", "error.general");
		}
		else {
			if (role.getRole() == null || role.getRole().equals("")) {
				errors.rejectValue("role", "error.role");
			}
			if (role.getDescription() == null || role.getDescription().equals("")) {
				errors.rejectValue("description", "error.description");
			}
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}