private void checkPatientIdentifiers(Patient patient) throws APIException {
		// TODO create a temporary identifier?
		if (patient.getIdentifiers().size() < 1 )
			throw new APIException("At least one Patient Identifier is required");
		
		// Check for duplicate identifiers
		for (PatientIdentifier pi : patient.getIdentifiers()) {
			if (!pi.isVoided()) {
				List<PatientIdentifier> ids = getPatientIdentifiers(pi.getIdentifier(), pi.getIdentifierType());
				for (PatientIdentifier id : ids) {
					if (!id.getIdentifierType().hasCheckDigit() || id.getPatient().equals(patient))
						continue;
					log.debug("Patient: " + patient.getPatientId());
					log.debug("Identifier: " + pi);
					throw new APIException("Identifier " + pi.getIdentifier() + " in use by patient #" + id.getPatient().getPatientId());
				}
			}
		}
		
	}