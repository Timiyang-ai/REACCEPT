public void validate(Object obj, Errors errors) {
		Privilege privilege = (Privilege)obj;
		if (privilege == null) {
			errors.rejectValue("privilege", "general.error");
		}
		else {
			if (privilege.getPrivilege() == null || privilege.getPrivilege().equals("")) {
				errors.rejectValue("privilege", "Privilege.privilege.error");
			}
			if (privilege.getDescription() == null || privilege.getDescription().equals("")) {
				errors.rejectValue("description", "general.description.error");
			}
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}