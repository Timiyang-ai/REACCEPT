private void checkPatientIdentifiers(Patient patient) throws APIException {
		// TODO create a temporary identifier?
		if (patient.getIdentifiers().size() < 1 )
			throw new APIException("At least one Patient Identifier is required");
		
		List<String> identifiersUsed = new Vector<String>();
		
		// Check for duplicate identifiers
		for (PatientIdentifier pi : patient.getIdentifiers()) {
			// skip voided identifiers
			if (pi.isVoided()) continue;
			
			// skip and remove invalid/empty identifiers
			if (pi.getIdentifier() == null || pi.getIdentifier().length() == 0) {
				patient.removeIdentifier(pi);
				continue;
			}
			
			// check this patient for duplicate identifiers
			if (pi.getIdentifierType().hasCheckDigit()) {
				if (identifiersUsed.contains(pi.getIdentifier()))
					throw new APIException("This patient has duplicate identifiers for: " + pi.getIdentifier());
				else
					identifiersUsed.add(pi.getIdentifier());
			}
				
			// compare against other identifiers matching the id string and id type
			Patient p = identifierInUse(pi.getIdentifier(), pi.getIdentifierType(), patient);
			if (p != null)
				throw new APIException("Identifier " + pi.getIdentifier() + " in use by patient #" + p.getPatientId());
		}
		
	}