public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		EncounterRole encounterRole = (EncounterRole) obj;
		if (!errors.hasErrors()) {
			EncounterRole duplicate = Context.getEncounterService().getEncounterRoleByName(encounterRole.getName().trim());
			if (duplicate != null && duplicate.getUuid() != null
			        && !OpenmrsUtil.nullSafeEquals(encounterRole.getUuid(), duplicate.getUuid())) {
				errors.rejectValue("name", "encounterRole.duplicate.name",
				    "Specified Encounter Role name already exists, please specify another ");
			}
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "name", "description", "retireReason");
		}
	}