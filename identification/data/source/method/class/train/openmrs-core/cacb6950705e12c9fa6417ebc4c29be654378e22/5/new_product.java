public List<PatientIdentifier> getPatientIdentifiers(PatientIdentifierType pit) {
		List<PatientIdentifier> ids = new ArrayList<>();
		for (PatientIdentifier pi : getIdentifiers()) {
			if (!pi.getVoided() && pit.equals(pi.getIdentifierType())) {
				ids.add(pi);
			}
		}
		return ids;
	}