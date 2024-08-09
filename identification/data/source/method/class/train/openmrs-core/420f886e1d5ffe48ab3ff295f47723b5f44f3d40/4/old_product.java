public void validate(Object obj, Errors errors) {
		Location location = (Location)obj;
		if (location == null) {
			errors.rejectValue("location", "general.error");
		}
		else {
			if (location.getName() == null || location.getName().equals("")) {
				errors.rejectValue("name", "general.name.error");
			}
			if (location.getDescription() == null || location.getDescription().equals("")) {
				errors.rejectValue("description", "general.description.error");
			}
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}