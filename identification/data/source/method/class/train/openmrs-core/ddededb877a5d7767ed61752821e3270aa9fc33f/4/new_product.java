private void checkPatientIdentifiers(Patient patient) throws APIException {
		// TODO create a temporary identifier?
		if (patient.getIdentifiers().size() < 1 )
			throw new APIException("At least one Patient Identifier is required");
		
		List<String> identifiersUsed = new Vector<String>();
		
		List<PatientIdentifier> identifiers = new Vector<PatientIdentifier>();
		identifiers.addAll(patient.getIdentifiers());
		
		// Check for duplicate identifiers
		for (PatientIdentifier pi : identifiers) {
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
			
			PatientIdentifierType pit = pi.getIdentifierType();
			String identifier = pi.getIdentifier();
			try {
				if (pit.hasCheckDigit() && !OpenmrsUtil.isValidCheckDigit(identifier)) {
					log.error("hasCheckDigit and is not valid: " + pit.getName() + " " + identifier);
					throw new APIException("Invalid check digit for identifier " + identifier);
				}
				//else if (pit.hasCheckDigit() == false && identifier.contains("-")) {
				//	log.error("hasn't CheckDigit and contains '-': " + pit.getName() + " " + identifier);
				//	throw new APIException("Invalid character for non-checkdigit identifier " + identifier);
				//}
			} catch (Exception e) {
				log.error("exception thrown with: " + pit.getName() + " " + identifier);
				log.error("Error while adding patient identifiers to savedIdentifier list", e);
				throw new APIException("Invalid identifier " + identifier);
			}
		}
		
	}