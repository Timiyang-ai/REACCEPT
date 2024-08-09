public Collection<ConceptDescription> getDescriptions() {
		if (descriptions == null) {
			descriptions = new HashSet<ConceptDescription>();
		}
		return descriptions;
	}