public void validate(Object obj, Errors errors) {
		EncounterType encounterType = (EncounterType)obj;
		if (encounterType == null) {
			errors.rejectValue("encounterType", "error.general");
		}
		else {
			if (encounterType.getName() == null || encounterType.getName().equals("")) {
				errors.rejectValue("name", "error.name");
			}
			if (encounterType.getDescription() == null || encounterType.getDescription().equals("")) {
				errors.rejectValue("description", "error.description");
			}
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}