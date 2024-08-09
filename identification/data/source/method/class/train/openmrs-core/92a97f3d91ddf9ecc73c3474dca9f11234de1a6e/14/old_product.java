public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		if (user == null) {
			errors.reject("error.general");
		} else {
			if (user.isRetired() && StringUtils.isBlank(user.getRetireReason())) {
				errors.rejectValue("retireReason", "error.null");
			}
			if (user.getPerson() == null) {
				errors.rejectValue("person", "error.null");
			} else {
				// check that required person details are filled out
				Person person = user.getPerson();
				if (person.getGender() == null) {
					errors.rejectValue("person.gender", "error.null");
				}
				if (person.getDead() == null) {
					errors.rejectValue("person.dead", "error.null");
				}
				if (person.getVoided() == null) {
					errors.rejectValue("person.voided", "error.null");
				}
				if (person.getPersonName() == null || StringUtils.isEmpty(person.getPersonName().getFullName())) {
					errors.rejectValue("person", "Person.names.length");
				}
			}
			
			AdministrationService as = Context.getAdministrationService();
			boolean emailAsUsername = false;
			try {
				Context.addProxyPrivilege(PrivilegeConstants.GET_GLOBAL_PROPERTIES);
				emailAsUsername = Boolean.parseBoolean(as.getGlobalProperty(
				    OpenmrsConstants.GLOBAL_PROPERTY_USER_REQUIRE_EMAIL_AS_USERNAME, "false"));
			}
			finally {
				Context.removeProxyPrivilege(PrivilegeConstants.GET_GLOBAL_PROPERTIES);
			}
			
			if (emailAsUsername) {
				boolean isValidUserName = isUserNameAsEmailValid(user.getUsername());
				if (!isValidUserName) {
					errors.rejectValue("username", "error.username.email");
				}
			} else {
				boolean isValidUserName = isUserNameValid(user.getUsername());
				if (!isValidUserName) {
					errors.rejectValue("username", "error.username.pattern");
				}
			}
		}
	}