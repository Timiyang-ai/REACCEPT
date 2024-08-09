public Set<PatientIdentifier> getIdentifiers() {
		if (identifiers == null)
			identifiers = new TreeSet<PatientIdentifier>();
		return this.identifiers;
	}