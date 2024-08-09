public Collection<ConceptName> getSynonyms() {
		return getNames().stream()
				.filter(n -> n.isSynonym())
				.collect(Collectors.toSet());
	}