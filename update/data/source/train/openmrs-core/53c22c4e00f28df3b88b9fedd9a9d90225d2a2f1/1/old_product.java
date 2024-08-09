public void addIdentifier(PatientIdentifier patientIdentifier) {
		patientIdentifier.setPatient(this);
		if (getIdentifiers() == null)
			identifiers = new TreeSet<PatientIdentifier>();
		if (patientIdentifier != null && !OpenmrsUtil.collectionContains(identifiers, patientIdentifier))
			identifiers.add(patientIdentifier);
	}