public void validate(Object obj, Errors errors) {
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ": Validating patient data from the short patient form....");
		
		ShortPatientModel shortPatientModel = (ShortPatientModel) obj;
		PersonName personName = shortPatientModel.getPersonName();
		
		//TODO We should be able to let developers and implementations to specify which person name 
		// fields should be used to determine uniqueness
		
		//check if this name has a unique givenName, middleName and familyName combination
		for (PersonName possibleDuplicate : shortPatientModel.getPatient().getNames()) {
			//don't compare the name to itself
			if (OpenmrsUtil.nullSafeEquals(possibleDuplicate.getId(), personName.getId()))
				continue;
			
			if (OpenmrsUtil.nullSafeEqualsIgnoreCase(possibleDuplicate.getGivenName(), personName.getGivenName())
			        && OpenmrsUtil.nullSafeEqualsIgnoreCase(possibleDuplicate.getMiddleName(), personName.getMiddleName())
			        && OpenmrsUtil.nullSafeEqualsIgnoreCase(possibleDuplicate.getFamilyName(), personName.getFamilyName())) {
				errors.reject("Patient.duplicateName", new Object[] { personName.toString() }, personName.toString()
				        + " is a duplicate name for the same patient");
			}
		}
		
		Errors nameErrors = new BindException(personName, "personName");
		new PersonNameValidator().validatePersonName(personName, nameErrors, false, true);
		
		if (nameErrors.hasErrors()) {
			// pick all the personName errors and bind them to the formObject
			Iterator<ObjectError> it = nameErrors.getAllErrors().iterator();
			Set<String> errorCodesWithNoArguments = new HashSet<String>();
			while (it.hasNext()) {
				ObjectError error = it.next();
				// don't show similar error message multiple times in the view
				// unless they take in arguments which will make them atleast different
				if (error.getCode() != null
				        && (!errorCodesWithNoArguments.contains(error.getCode()) || (error.getArguments() != null && error
				                .getArguments().length > 0))) {
					errors.reject(error.getCode(), error.getArguments(), "");
					if (error.getArguments() == null || error.getArguments().length == 0)
						errorCodesWithNoArguments.add(error.getCode());
				}
			}
			// drop the collection
			errorCodesWithNoArguments = null;
		}
		
		//TODO We should be able to let developers and implementations to specify which
		// person address fields should be used to determine uniqueness
		
		//check if this address is unique
		PersonAddress personAddress = shortPatientModel.getPersonAddress();
		for (PersonAddress possibleDuplicate : shortPatientModel.getPatient().getAddresses()) {
			//don't compare the address to itself
			if (OpenmrsUtil.nullSafeEquals(possibleDuplicate.getId(), personAddress.getId()))
				continue;
			
			if (!possibleDuplicate.isBlank() && !personAddress.isBlank()
			        && possibleDuplicate.toString().equalsIgnoreCase(personAddress.toString())) {
				errors.reject("Patient.duplicateAddress", new Object[] { personAddress.toString() }, personAddress
				        .toString()
				        + " is a duplicate address for the same patient");
			}
		}
		
		if (CollectionUtils.isEmpty(shortPatientModel.getIdentifiers()))
			errors.reject("PatientIdentifier.error.insufficientIdentifiers");
		else {
			boolean nonVoidedIdentifierFound = false;
			for (PatientIdentifier pId : shortPatientModel.getIdentifiers()) {
				//no need to validate unsaved identifiers that have been removed
				if (pId.getPatientIdentifierId() == null && pId.isVoided())
					continue;
				
				if (!pId.isVoided())
					nonVoidedIdentifierFound = true;
				
				new PatientIdentifierValidator().validate(pId, errors);
			}
			// if all the names are voided
			if (!nonVoidedIdentifierFound)
				errors.reject("PatientIdentifier.error.insufficientIdentifiers");
			
		}
		
		// Make sure they chose a gender
		if (StringUtils.isBlank(shortPatientModel.getPatient().getGender()))
			errors.rejectValue("patient.gender", "Person.gender.required");
		
		// check patients birthdate against future dates and really old dates
		if (shortPatientModel.getPatient().getBirthdate() != null) {
			if (shortPatientModel.getPatient().getBirthdate().after(new Date()))
				errors.rejectValue("patient.birthdate", "error.date.future");
			else {
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				c.add(Calendar.YEAR, -120); // patient cannot be older than 120
				// years old
				if (shortPatientModel.getPatient().getBirthdate().before(c.getTime())) {
					errors.rejectValue("patient.birthdate", "error.date.nonsensical");
				}
			}
		} else {
			errors.rejectValue("patient.birthdate", "error.required", new Object[] { Context.getMessageSourceService()
			        .getMessage("Person.birthdate") }, "");
		}
		
		//validate the personAddress
		if (shortPatientModel.getPersonAddress() != null) {
			try {
				errors.pushNestedPath("personAddress");
				ValidationUtils.invokeValidator(new PersonAddressValidator(), shortPatientModel.getPersonAddress(), errors);
			}
			finally {
				errors.popNestedPath();
			}
		}
		
		if (shortPatientModel.getPatient().getDead()) {
			if (shortPatientModel.getPatient().getCauseOfDeath() == null)
				errors.rejectValue("patient.causeOfDeath", "Person.dead.causeOfDeathNull");
			
			if (shortPatientModel.getPatient().getDeathDate() != null) {
				if (shortPatientModel.getPatient().getDeathDate().after(new Date()))
					errors.rejectValue("patient.deathDate", "error.date.future");
				// death date has to be after birthdate if both are specified
				if (shortPatientModel.getPatient().getBirthdate() != null
				        && shortPatientModel.getPatient().getDeathDate().before(
				            shortPatientModel.getPatient().getBirthdate()))
					errors.rejectValue("patient.deathDate", "error.deathdate.before.birthdate");
			}
		}
	}