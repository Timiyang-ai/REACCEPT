public void validate(Object obj, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		if (log.isDebugEnabled())
			log.debug(this.getClass().getName() + ".validate...");
		
		if (obj == null) {
			return;
		}
		
		super.validate(obj, errors);
		
		Patient patient = (Patient) obj;
		
		// Make sure they chose a preferred ID
		Boolean preferredIdentifierChosen = false;
		//Voided patients have only voided identifiers since they were voided with the patient, 
		//so get all otherwise get the active ones
		Collection<PatientIdentifier> identifiers = patient.isVoided() ? patient.getIdentifiers() : patient
		        .getActiveIdentifiers();
		for (PatientIdentifier pi : identifiers) {
			if (pi.isPreferred()) {
				preferredIdentifierChosen = true;
			}
		}
		if (!preferredIdentifierChosen && identifiers.size() != 1) {
			errors.reject("error.preferredIdentifier");
		}
		
		if (!errors.hasErrors()) {
			// Validate PatientIdentifers
			if (patient != null && patient.getIdentifiers() != null) {
				for (PatientIdentifier identifier : patient.getIdentifiers()) {
					patientIdentifierValidator.validate(identifier, errors);
				}
			}
		}
	}