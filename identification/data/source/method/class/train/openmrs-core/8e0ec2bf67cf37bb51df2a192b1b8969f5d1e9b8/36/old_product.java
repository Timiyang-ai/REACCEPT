public Collection<ConceptName> getSynonyms() {
		Collection<ConceptName> synonyms = new Vector<ConceptName>();
		for (ConceptName possibleSynonym : getNames()) {
			if (possibleSynonym.isSynonym()) {
				synonyms.add(possibleSynonym);
			}
		}
		log.debug("returning: " + synonyms);
		return synonyms;
	}