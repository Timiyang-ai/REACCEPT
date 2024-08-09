public void validate(Object obj, Errors errors) {
		EncounterType encounterType = (EncounterType)obj;
		if (encounterType == null) {
			errors.rejectValue("encounterType", "general.error");
		}
		else {
			if (encounterType.getName() == null || encounterType.getName().equals("")) {
				errors.rejectValue("name", "general.name.error");
			}
			if (encounterType.getDescription() == null || encounterType.getDescription().equals("")) {
				errors.rejectValue("description", "general.description.error");
			}
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}