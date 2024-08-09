public void validate(Object obj, Errors errors) {
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ".validate...");
		
		Patient patient = (Patient) obj;
		
		if (patient != null) {
			for (PersonName personName : patient.getNames()) {
				personNameValidator.validate(personName, errors);
			}
			
			//validate the personAddress
			int index = 0;
			for (PersonAddress address : patient.getAddresses()) {
				try {
					errors.pushNestedPath("addresses[" + index + "]");
					ValidationUtils.invokeValidator(personAddressValidator, address, errors);
				}
				finally {
					errors.popNestedPath();
					index++;
				}
			}
		}
		
		// Make sure they choose a gender
		if (StringUtils.isBlank(patient.getGender()))
			errors.rejectValue("gender", "Person.gender.required");
		
		// check patients birthdate against future dates and really old dates
		if (patient.getBirthdate() != null) {
			if (patient.getBirthdate().after(new Date()))
				errors.rejectValue("birthdate", "error.date.future");
			else {
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				c.add(Calendar.YEAR, -120); // patient cannot be older than 120 years old 
				if (patient.getBirthdate().before(c.getTime())) {
					errors.rejectValue("birthdate", "error.date.nonsensical");
				}
			}
		}
		
		//	 Patient Info 
		if (patient.isVoided())
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "voidReason", "error.null");
		if (patient.isDead() && (patient.getCauseOfDeath() == null))
			errors.rejectValue("causeOfDeath", "Patient.dead.causeOfDeathNull");
		
		if (!errors.hasErrors()) {
			// Validate PatientIdentifers
			if (patient != null && patient.getIdentifiers() != null) {
				for (PatientIdentifier identifier : patient.getIdentifiers()) {
					patientIdentifierValidator.validate(identifier, errors);
				}
			}
		}
	}