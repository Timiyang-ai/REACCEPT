public void validate(Object obj, Errors errors) {
		EncounterType encounterType = (EncounterType) obj;
		if (encounterType == null) {
			errors.rejectValue("encounterType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			
			if (!errors.hasErrors()) {
				EncounterType duplicate = Context.getEncounterService().getEncounterType(encounterType.getName().trim());
				if (duplicate != null) {
					if (duplicate.getUuid() != null
					        && !OpenmrsUtil.nullSafeEquals(encounterType.getUuid(), duplicate.getUuid())) {
						errors.rejectValue("name", "encounterType.duplicate.name",
						    "Specified Encounter Type name already exists, please specify another ");
					}
				}
			}
		}
	}