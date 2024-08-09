public Collection<ConceptName> getNames(boolean includeVoided) {
		if (names == null) {
			names = new HashSet<ConceptName>();
		}

		return names.stream()
				.filter(n -> includeVoided || !n.getVoided())
				.collect(Collectors.toSet());
	}