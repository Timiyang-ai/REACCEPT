public void validate(Object obj, Errors errors) {
		Location location = (Location)obj;
		if (location == null) {
			errors.rejectValue("location", "error.general");
		}
		else {
			if (location.getName() == null || location.getName().equals("")) {
				errors.rejectValue("name", "error.name");
			}
			if (location.getDescription() == null || location.getDescription().equals("")) {
				errors.rejectValue("description", "error.description");
			}
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}