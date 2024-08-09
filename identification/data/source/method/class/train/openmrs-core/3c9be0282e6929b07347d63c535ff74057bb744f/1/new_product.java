public Set<PatientIdentifier> getIdentifiers() {
		if (identifiers == null) {
			identifiers = new LinkedHashSet<PatientIdentifier>();
		}
		return this.identifiers;
	}