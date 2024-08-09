public List<PatientIdentifier> getIdentifiers() {
		if (identifiers == null)
			identifiers = new LinkedList<PatientIdentifier>();
		return this.identifiers;
	}