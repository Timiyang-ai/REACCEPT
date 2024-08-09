public void validate(Object obj, Errors errors) {
		PatientIdentifierType identifierType = (PatientIdentifierType)obj;
		if (identifierType == null) {
			errors.rejectValue("identifierType", "error.general");
		}
		else {
			if (identifierType.getName() == null || identifierType.getName().equals("")) {
				errors.rejectValue("name", "error.name");
			}
			if (identifierType.getDescription() == null || identifierType.getDescription().equals("")) {
				errors.rejectValue("description", "error.description");
			}
		}
	}