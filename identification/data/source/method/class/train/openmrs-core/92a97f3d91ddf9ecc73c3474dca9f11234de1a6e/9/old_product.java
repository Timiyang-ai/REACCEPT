public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		if (user == null) {
			errors.rejectValue("user", "error.general");
		} else {
			if (user.isRetired() && !StringUtils.hasText(user.getRetireReason()))
				errors.rejectValue("retireReason", "error.null");
			if (user.getPerson() == null) {
				errors.rejectValue("person", "error.null");
			} else {
				// check that required person details are filled out
				Person person = user.getPerson();
				if (person.getGender() == null)
					errors.rejectValue("person.gender", "error.null");
				if (person.getDead() == null)
					errors.rejectValue("person.dead", "error.null");
				if (person.getVoided() == null)
					errors.rejectValue("person.voided", "error.null");
				if (person.getPersonName() == null || !StringUtils.hasText(person.getPersonName().toString()))
					errors.rejectValue("person", "Person.names.length");
			}
		}
		
		if (!isUserNameValid(user.getUsername()))
			errors.rejectValue("username", "error.username.pattern");
	}