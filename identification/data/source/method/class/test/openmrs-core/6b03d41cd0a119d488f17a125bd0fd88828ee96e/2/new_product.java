public List<PatientIdentifier> getActiveIdentifiers() {
		List<PatientIdentifier> ids = new Vector<PatientIdentifier>();
		if (identifiers != null) {
			for (PatientIdentifier pi : identifiers) {
				if (pi.isVoided() == false)
					ids.add(pi);
			}
		}
		return ids;
	}