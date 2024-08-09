public void addIdentifier(PatientIdentifier patientIdentifier) {
		if (patientIdentifier != null) {
			patientIdentifier.setPatient(this);
			// make sure the set doesn't already contain an identifier with the same
			// identifier, identifierType
			for (PatientIdentifier currentId : getActiveIdentifiers()) {
				if (currentId.equalsContent(patientIdentifier)) {
					return; // fail silently if someone tries to add a duplicate
				}
			}
		}

		if (identifiers == null) {
			identifiers = new HashSet<PatientIdentifier>();
		}
		identifiers.add(patientIdentifier);
	}