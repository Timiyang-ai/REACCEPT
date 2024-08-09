public void removeIdentifier(PatientIdentifier patientIdentifier) {
		if (getIdentifiers() != null)
			identifiers.remove(patientIdentifier);
	}