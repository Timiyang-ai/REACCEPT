public void validate(Object obj, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		EncounterType encounterType = (EncounterType) obj;
		if (encounterType == null) {
			errors.rejectValue("encounterType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
	}