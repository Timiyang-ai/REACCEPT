public void removeIdentifier(PatientIdentifier patientIdentifier) {
		if (getIdentifiers() != null && patientIdentifier != null) {
			identifiers.remove(patientIdentifier);
		}
	}