public void validate(Object obj, Errors errors) {
		EncounterType encounterType = (EncounterType) obj;
		if (encounterType == null) {
			errors.rejectValue("encounterType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "localizedName.unlocalizedValue",
			    "LocalizedName.unlocalizedName.empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
	}