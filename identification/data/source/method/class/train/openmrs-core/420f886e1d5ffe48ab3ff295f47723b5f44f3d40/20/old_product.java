public void validate(Object obj, Errors errors) {
		Privilege privilege = (Privilege)obj;
		if (privilege == null) {
			errors.rejectValue("privilege", "error.general");
		}
		else {
			if (privilege.getPrivilege() == null || privilege.getPrivilege().equals("")) {
				errors.rejectValue("privilege", "error.privilege");
			}
			if (privilege.getDescription() == null || privilege.getDescription().equals("")) {
				errors.rejectValue("description", "error.description");
			}
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}