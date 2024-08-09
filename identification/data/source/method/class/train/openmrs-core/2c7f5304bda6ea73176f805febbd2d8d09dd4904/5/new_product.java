public static void validateIdentifier(PatientIdentifier pi) throws PatientIdentifierException {
		
		// Validate that the identifier is non-null
		if (pi == null) {
			throw new BlankIdentifierException("PatientIdentifier.error.null");
		}
		
		// Only validate if the PatientIdentifier is not voided
		if (!pi.getVoided()) {
			
			// Check that this is a valid identifier
			validateIdentifier(pi.getIdentifier(), pi.getIdentifierType());
			
			// Check that location is included if it is required (default behavior is to require it)
			LocationBehavior lb = pi.getIdentifierType().getLocationBehavior();
			if (pi.getLocation() == null && (lb == null || lb == LocationBehavior.REQUIRED)) {
				String identifierString = (pi.getIdentifier() != null) ? pi.getIdentifier() : "";
				throw new PatientIdentifierException(Context.getMessageSourceService().getMessage(
				    "PatientIdentifier.location.null", new Object[] { identifierString }, Context.getLocale()));
			}
			
			if (pi.getIdentifierType().getUniquenessBehavior() != UniquenessBehavior.NON_UNIQUE
			        && Context.getPatientService().isIdentifierInUseByAnotherPatient(pi)) {
				// Check is already in use by another patient
				throw new IdentifierNotUniqueException(Context.getMessageSourceService().getMessage(
				    "PatientIdentifier.error.notUniqueWithParameter", new Object[] { pi.getIdentifier() },
				    Context.getLocale()), pi);
			}
		}
	}