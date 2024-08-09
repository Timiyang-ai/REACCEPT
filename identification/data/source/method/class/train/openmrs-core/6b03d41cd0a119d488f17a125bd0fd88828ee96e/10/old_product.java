public void addIdentifier(PatientIdentifier patientIdentifier) {
		patientIdentifier.setPatient(this);
		if (identifiers == null)
			identifiers = new HashSet<PatientIdentifier>();
		if (!identifiers.contains(patientIdentifier)
				&& patientIdentifier != null)
			identifiers.add(patientIdentifier);
	}