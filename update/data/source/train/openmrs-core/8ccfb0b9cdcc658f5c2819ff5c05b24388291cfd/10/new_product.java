public CohortMembership getActiveMembership(Patient patient) {
		return getMemberships().stream()
				.filter(m -> m.isActive() && m.getPatientId().equals(patient.getPatientId()))
				.collect(Collectors.toList())
				.get(0);
	}