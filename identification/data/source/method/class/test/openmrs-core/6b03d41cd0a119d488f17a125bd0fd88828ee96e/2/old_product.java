public List<PatientIdentifier> getActiveIdentifiers() {
		List<PatientIdentifier> ids = new Vector<PatientIdentifier>();
		if (identifiers != null && identifiers.size() > 0) {
			for (PatientIdentifier pi : identifiers) {
				if (pi.isVoided() == false)
					ids.add(pi);
			}
		}
		return ids;
	}