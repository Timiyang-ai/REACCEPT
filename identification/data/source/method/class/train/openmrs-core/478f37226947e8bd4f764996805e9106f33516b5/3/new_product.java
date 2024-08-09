public void removeIdentifier(PatientIdentifier patientIdentifier) {
		if (patientIdentifier != null) {
			getIdentifiers().remove(patientIdentifier);
		}
	}