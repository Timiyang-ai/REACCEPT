public void validate(Object obj, Errors errors) {
		Role role = (Role)obj;
		if (role == null) {
			errors.rejectValue("role", "general.error");
		}
		else {
			if (role.getRole() == null || role.getRole().equals("")) {
				errors.rejectValue("role", "Role.name.error");
			}
			if (role.getDescription() == null || role.getDescription().equals("")) {
				errors.rejectValue("description", "general.description.error");
			}
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}