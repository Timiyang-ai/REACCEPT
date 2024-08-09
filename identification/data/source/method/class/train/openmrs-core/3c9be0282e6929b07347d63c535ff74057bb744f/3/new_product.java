public Set<PatientIdentifier> getIdentifiers() {
		if (identifiers == null)
			identifiers = new HashSet<PatientIdentifier>();
		return this.identifiers;
	}