@SuppressWarnings("unchecked")
	public Collection<Object> findPatients(String searchValue, boolean includeVoided, Integer start, Integer length) {
		if (maximumResults == null)
			maximumResults = getMaximumSearchResults();
		if (length != null && length > maximumResults)
			length = maximumResults;
		
		// the list to return
		List<Object> patientList = new Vector<Object>();
		
		PatientService ps = Context.getPatientService();
		Collection<Patient> patients;
		
		try {
			patients = ps.getPatients(searchValue, start, length);
		}
		catch (APIAuthenticationException e) {
			patientList.add(Context.getMessageSourceService().getMessage("Patient.search.error") + " - " + e.getMessage());
			return patientList;
		}
		
		patientList = new Vector<Object>(patients.size());
		for (Patient p : patients)
			patientList.add(new PatientListItem(p));
		// if the length wasn't limited to less than 3 or this is the second ajax call
		// and only 2 results found and a number was not in the
		// search, then do a decapitated search: trim each word
		// down to the first three characters and search again
		if ((length == null || length > 2) && patients.size() < 3 && !searchValue.matches(".*\\d+.*")) {
			String[] names = searchValue.split(" ");
			String newSearch = "";
			for (String name : names) {
				if (name.length() > 3)
					name = name.substring(0, 4);
				newSearch += " " + name;
			}
			newSearch = newSearch.trim();
			
			if (!newSearch.equals(searchValue)) {
				Collection<Patient> newPatients = ps.getPatients(newSearch, start, length);
				patients = CollectionUtils.union(newPatients, patients); // get unique hits
				//reconstruct the results list
				if (newPatients.size() > 0) {
					patientList = new Vector<Object>(patients.size());
					//patientList.add("Minimal patients returned. Results for <b>" + newSearch + "</b>");
					for (Patient p : newPatients) {
						PatientListItem pi = new PatientListItem(p);
						patientList.add(pi);
					}
				}
			}
		}
		//no results found and a number was in the search --
		//should check whether the check digit is correct.
		else if (patients.size() == 0 && searchValue.matches(".*\\d+.*")) {
			
			//Looks through all the patient identifier validators to see if this type of identifier
			//is supported for any of them.  If it isn't, then no need to warn about a bad check
			//digit.  If it does match, then if any of the validators validates the check digit
			//successfully, then the user is notified that the identifier has been entered correctly.
			//Otherwise, the user is notified that the identifier was entered incorrectly.
			
			Collection<IdentifierValidator> pivs = ps.getAllIdentifierValidators();
			boolean shouldWarnUser = true;
			boolean validCheckDigit = false;
			boolean identifierMatchesValidationScheme = false;
			
			for (IdentifierValidator piv : pivs) {
				try {
					if (piv.isValid(searchValue)) {
						shouldWarnUser = false;
						validCheckDigit = true;
					}
					identifierMatchesValidationScheme = true;
				}
				catch (UnallowedIdentifierException e) {}
			}
			
			if (identifierMatchesValidationScheme) {
				if (shouldWarnUser)
					patientList
					        .add("<p style=\"color:red; font-size:big;\"><b>WARNING: Identifier has been typed incorrectly!  Please double check the identifier.</b></p>");
				else if (validCheckDigit)
					patientList
					        .add("<p style=\"color:green; font-size:big;\"><b>This identifier has been entered correctly, but still no patients have been found.</b></p>");
			}
		}
		
		return patientList;
	}