public void validate(Object obj, Errors errors) {
		PatientIdentifierType identifierType = (PatientIdentifierType) obj;
		if (identifierType == null) {
			errors.rejectValue("identifierType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidateUtil.validateFieldLengths(errors, identifierType.getClass(), "name", "description", "format");
			PatientIdentifierType exist = Context.getPatientService().getPatientIdentifierTypeByName(
			    identifierType.getName());
			if (exist != null && !exist.isRetired()
			        && !OpenmrsUtil.nullSafeEquals(identifierType.getUuid(), exist.getUuid())) {
				errors.rejectValue("name", "identifierType.duplicate.name");
			}
		}
	}