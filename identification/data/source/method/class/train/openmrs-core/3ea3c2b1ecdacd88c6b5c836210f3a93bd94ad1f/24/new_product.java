@Deprecated
	public void validatePersonName(PersonName personName, Errors errors, boolean arrayInd, boolean testInd) {
		
		if (personName == null) {
			errors.reject("error.name");
			return;
		}
		// Make sure they assign a name
		if (StringUtils.isBlank(personName.getGivenName())
		        || StringUtils.isBlank(personName.getGivenName().replaceAll("\"", ""))) {
			errors.rejectValue(getFieldKey("givenName", arrayInd, testInd), "Patient.names.required.given.family");
		}

		// Make sure the entered name value is sensible 
		String namePattern = Context.getAdministrationService().getGlobalProperty(
		    OpenmrsConstants.GLOBAL_PROPERTY_PATIENT_NAME_REGEX);
		if (StringUtils.isNotBlank(namePattern)) {
			if (StringUtils.isNotBlank(personName.getGivenName()) && !personName.getGivenName().matches(namePattern)) {
				errors.rejectValue(getFieldKey("givenName", arrayInd, testInd), "GivenName.invalid");
			}
			if (StringUtils.isNotBlank(personName.getMiddleName()) && !personName.getMiddleName().matches(namePattern)) {
				errors.rejectValue(getFieldKey("middleName", arrayInd, testInd), "MiddleName.invalid");
			}
			if (StringUtils.isNotBlank(personName.getFamilyName()) && !personName.getFamilyName().matches(namePattern)) {
				errors.rejectValue(getFieldKey("familyName", arrayInd, testInd), "FamilyName.invalid");
			}
			if (StringUtils.isNotBlank(personName.getFamilyName2()) && !personName.getFamilyName2().matches(namePattern)) {
				errors.rejectValue(getFieldKey("familyName2", arrayInd, testInd), "FamilyName2.invalid");
			}
		}
		ValidateUtil.validateFieldLengths(errors, personName.getClass(), "prefix", "givenName", "middleName",
		    "familyNamePrefix", "familyName", "familyName2", "familyNameSuffix", "degree", "voidReason");
	}