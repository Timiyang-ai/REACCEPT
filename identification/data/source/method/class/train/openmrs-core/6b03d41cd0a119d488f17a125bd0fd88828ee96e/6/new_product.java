public List<PatientIdentifier> getActiveIdentifiers() {
		List<PatientIdentifier> ids = new Vector<PatientIdentifier>();
		if (getIdentifiers() != null) {
			List<PatientIdentifier> nonPreferred = new LinkedList<PatientIdentifier>();
			for (PatientIdentifier pi : getIdentifiers()) {
				if (pi.isVoided() == false) {
					if (pi.isPreferred())
						ids.add(pi);
					else
						nonPreferred.add(pi);
				}
			}
			for (PatientIdentifier pi : nonPreferred)
				ids.add(pi);
		}
		return ids;
	}