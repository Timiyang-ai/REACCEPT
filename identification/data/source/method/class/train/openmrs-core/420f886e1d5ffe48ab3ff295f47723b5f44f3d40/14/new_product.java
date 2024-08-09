@Override
	public void validate(Object obj, Errors errors) {
		if (log.isDebugEnabled()) {
			log.debug(this.getClass().getName() + ".validate...");
		}
		
		if (obj == null) {
			return;
		}
		
		super.validate(obj, errors);
		
		Patient patient = (Patient) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "Person.gender.required");
		
		// Make sure they chose a preferred ID
		Boolean preferredIdentifierChosen = false;
		//Voided patients have only voided identifiers since they were voided with the patient, 
		//so get all otherwise get the active ones
		Collection<PatientIdentifier> identifiers = patient.getVoided() ? patient.getIdentifiers() : patient
		        .getActiveIdentifiers();
		for (PatientIdentifier pi : identifiers) {
			if (pi.getPreferred()) {
				preferredIdentifierChosen = true;
			}
		}
		if (!preferredIdentifierChosen && identifiers.size() != 1) {
			errors.reject("error.preferredIdentifier");
		}
		int index = 0;
		if (!errors.hasErrors() && patient.getIdentifiers() != null) {
			// Validate PatientIdentifers
			for (PatientIdentifier identifier : patient.getIdentifiers()) {
				errors.pushNestedPath("identifiers[" + index + "]");
				patientIdentifierValidator.validate(identifier, errors);
				errors.popNestedPath();
				index++;
			}
		}
		ValidateUtil.validateFieldLengths(errors, obj.getClass(), "voidReason");
	}