public void validate(Object obj, Errors errors) {
		PatientIdentifierType identifierType = (PatientIdentifierType)obj;
		if (identifierType == null) {
			errors.rejectValue("identifierType", "general.error");
		}
		else {
			if (identifierType.getName() == null || identifierType.getName().equals("")) {
				errors.rejectValue("name", "general.name.error");
			}
			if (identifierType.getDescription() == null || identifierType.getDescription().equals("")) {
				errors.rejectValue("description", "general.description.error");
			}
		}
	}