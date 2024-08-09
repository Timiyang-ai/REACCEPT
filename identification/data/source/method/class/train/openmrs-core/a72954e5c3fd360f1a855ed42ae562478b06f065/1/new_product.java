public Collection<CohortMembership> getMemberships(boolean includeVoided) {
		if (includeVoided) {
			return getMemberships();
		}
		return getMemberships().stream().filter(m -> m.getVoided() == includeVoided).collect(Collectors.toList());
	}