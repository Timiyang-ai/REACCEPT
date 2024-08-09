public void validatePersonName(PersonName personName, Errors errors, boolean arrayInd, boolean testInd) {
		
		if (personName == null)
			errors.reject("error.name");
		// Make sure they assign a name
		if (StringUtils.isBlank(personName.getGivenName())
		        || StringUtils.isBlank(personName.getGivenName().replaceAll("\"", "")))
			errors.rejectValue(getFieldKey("givenName", arrayInd, testInd), "Patient.names.required.given.family");
		if (StringUtils.isBlank(personName.getFamilyName())
		        || StringUtils.isBlank(personName.getFamilyName().replaceAll("\"", "")))
			errors.rejectValue(getFieldKey("familyName", arrayInd, testInd), "Patient.names.required.given.family");
		// Make sure the entered name value is sensible 
		String namePattern = Context.getAdministrationService().getGlobalProperty(
		    OpenmrsConstants.GLOBAL_PROPERTY_PATIENT_NAME_REGEX);
		if (StringUtils.isNotBlank(namePattern)) {
			if (StringUtils.isNotBlank(personName.getGivenName()) && !personName.getGivenName().matches(namePattern))
				errors.rejectValue(getFieldKey("givenName", arrayInd, testInd), "GivenName.invalid");
			if (StringUtils.isNotBlank(personName.getMiddleName()) && !personName.getMiddleName().matches(namePattern))
				errors.rejectValue(getFieldKey("middleName", arrayInd, testInd), "MiddleName.invalid");
			if (StringUtils.isNotBlank(personName.getFamilyName()) && !personName.getFamilyName().matches(namePattern))
				errors.rejectValue(getFieldKey("familyName", arrayInd, testInd), "FamilyName.invalid");
			if (StringUtils.isNotBlank(personName.getFamilyName2()) && !personName.getFamilyName2().matches(namePattern))
				errors.rejectValue(getFieldKey("familyName2", arrayInd, testInd), "FamilyName2.invalid");
		}
		// Make sure the length does not exceed database column size
		if (StringUtils.length(personName.getPrefix()) > 50)
			rejectPersonNameOnLength(errors, "prefix", arrayInd, testInd);
		if (StringUtils.length(personName.getGivenName()) > 50)
			rejectPersonNameOnLength(errors, "givenName", arrayInd, testInd);
		if (StringUtils.length(personName.getMiddleName()) > 50)
			rejectPersonNameOnLength(errors, "middleName", arrayInd, testInd);
		if (StringUtils.length(personName.getFamilyNamePrefix()) > 50)
			rejectPersonNameOnLength(errors, "familyNamePrefix", arrayInd, testInd);
		if (StringUtils.length(personName.getFamilyName()) > 50)
			rejectPersonNameOnLength(errors, "familyName", arrayInd, testInd);
		if (StringUtils.length(personName.getFamilyName2()) > 50)
			rejectPersonNameOnLength(errors, "familyName2", arrayInd, testInd);
		if (StringUtils.length(personName.getFamilyNameSuffix()) > 50)
			rejectPersonNameOnLength(errors, "familyNameSuffix", arrayInd, testInd);
		if (StringUtils.length(personName.getDegree()) > 50)
			rejectPersonNameOnLength(errors, "degree", arrayInd, testInd);
	}