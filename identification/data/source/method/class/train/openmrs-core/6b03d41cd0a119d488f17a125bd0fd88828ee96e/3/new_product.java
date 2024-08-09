public List<PatientIdentifier> getActiveIdentifiers() {
		List<PatientIdentifier> ids = new ArrayList<>();
		List<PatientIdentifier> nonPreferred = new LinkedList<>();
		for (PatientIdentifier pi : getIdentifiers()) {
			if (!pi.getVoided()) {
				if (pi.getPreferred()) {
					ids.add(pi);
				} else {
					nonPreferred.add(pi);
				}
			}
		}
		ids.addAll(nonPreferred);
		return ids;
	}