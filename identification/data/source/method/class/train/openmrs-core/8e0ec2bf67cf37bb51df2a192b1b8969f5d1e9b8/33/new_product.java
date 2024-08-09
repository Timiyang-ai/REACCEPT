public Collection<ConceptName> getSynonyms() {
		return getNames().stream()
				.filter(ConceptName::isSynonym)
				.collect(Collectors.toSet());
	}