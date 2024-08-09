public List<CohortMembership> getMemberships(boolean includeVoided) {
		return getMemberships().stream()
				.filter(m -> m.getVoided() == includeVoided)
				.collect(Collectors.toList());
	}