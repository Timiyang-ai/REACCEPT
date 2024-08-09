public void validatePersonName(PersonName personName, Errors errors, boolean arrayInd, boolean testInd) {
		
		if (personName == null) 
			errors.reject("error.name");
		// Make sure they assign a name
		if (StringUtils.isBlank(personName.getGivenName()) || StringUtils.isBlank(personName.getGivenName().replaceAll("\"", ""))) 
			errors.rejectValue(getFieldKey("givenName", arrayInd, testInd),"Patient.names.required.given.family");
		if (StringUtils.isBlank(personName.getFamilyName()) || StringUtils.isBlank(personName.getFamilyName().replaceAll("\"", ""))) 
			errors.rejectValue(getFieldKey("familyName", arrayInd, testInd),"Patient.names.required.given.family");

		// Make sure the length does not exceed database column size
		if (StringUtils.length(personName.getPrefix()) > 50) rejectPersonName(errors, "prefix", arrayInd, testInd);
		if (StringUtils.length(personName.getGivenName()) > 50) rejectPersonName(errors, "givenName", arrayInd, testInd);
		if (StringUtils.length(personName.getMiddleName()) > 50) rejectPersonName(errors, "middleName", arrayInd, testInd);
		if (StringUtils.length(personName.getFamilyNamePrefix()) > 50) rejectPersonName(errors, "familyNamePrefix", arrayInd, testInd);
		if (StringUtils.length(personName.getFamilyName()) > 50) rejectPersonName(errors, "familyName", arrayInd, testInd);
		if (StringUtils.length(personName.getFamilyName2()) > 50) rejectPersonName(errors, "familyName2", arrayInd, testInd);
		if (StringUtils.length(personName.getFamilyNameSuffix()) > 50) rejectPersonName(errors, "familyNameSuffix", arrayInd, testInd);
		if (StringUtils.length(personName.getDegree()) > 50) rejectPersonName(errors, "degree", arrayInd, testInd);
		
	}