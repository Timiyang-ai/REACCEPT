public void addIdentifier(PatientIdentifier patientIdentifier) {
		patientIdentifier.setPatient(this);
		if (getIdentifiers() == null)
			identifiers = new LinkedHashSet<PatientIdentifier>();
		if (patientIdentifier != null) {
			// make sure the set doesn't already contain an identifier with the same
			// identifier, identifierType
			for (PatientIdentifier currentId : getActiveIdentifiers()) {
				if (currentId.equalsContent(patientIdentifier)) {
					return; // fail silently if someone tries to add a duplicate
				}
			}
		}
		identifiers.add(patientIdentifier);
	}