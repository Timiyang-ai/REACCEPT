public Set<PatientIdentifier> getIdentifiers() {
		if (identifiers == null) {
			identifiers = new TreeSet<>();
		}
		return this.identifiers;
	}