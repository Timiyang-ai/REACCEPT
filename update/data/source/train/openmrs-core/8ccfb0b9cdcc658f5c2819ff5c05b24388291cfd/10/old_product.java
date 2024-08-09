public List<CohortMembership> getMemberships(Date asOf) {
		return getMembers().stream()
				.filter(m -> !m.getStartDate().before(asOf)).collect(Collectors.toList());
	}