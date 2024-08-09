public void addIdentifier(PatientIdentifier patientIdentifier) {
		patientIdentifier.setPatient(this);
		if (identifiers == null)
			identifiers = new HashSet<PatientIdentifier>();
		if (patientIdentifier != null && !identifiers.contains(patientIdentifier))
			identifiers.add(patientIdentifier);
	}