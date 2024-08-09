public List<PatientIdentifier> getPatientIdentifiers(PatientIdentifierType pit) {
		List<PatientIdentifier> ids = new Vector<PatientIdentifier>();
		if (getIdentifiers() != null) {
			for (PatientIdentifier pi : getIdentifiers()) {
				if (!pi.isVoided() && pit.equals(pi.getIdentifierType())) {
					ids.add(pi);
				}
			}
		}
		return ids;
	}