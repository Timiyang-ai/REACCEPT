public void validate(Object obj, Errors errors) {
		EncounterType encounterType = (EncounterType) obj;
		if (encounterType == null) {
			errors.rejectValue("encounterType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			
			if (!errors.hasErrors()) {
				EncounterType duplicate = Context.getEncounterService().getEncounterType(encounterType.getName().trim());
				if (duplicate != null && !OpenmrsUtil.nullSafeEquals(encounterType.getUuid(), duplicate.getUuid())
				        && !duplicate.isRetired()) {
					errors.rejectValue("name", "EncounterType.error.duplicateEncounterTypeNameSpecified",
					    "Specified Encounter Type name already exists, please specify another ");
				}
			}
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "name", "retireReason");
		}
	}