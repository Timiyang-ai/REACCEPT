public Collection<ConceptName> getNames(Locale locale) {
		return getNames().stream()
				.filter(n -> n.getLocale().equals(locale))
				.collect(Collectors.toSet());
	}