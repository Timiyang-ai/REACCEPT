public Collection<ConceptName> getSynonyms(Locale locale) {
		
		Collection<ConceptName> syns = new Vector<ConceptName>();
		for (ConceptName possibleSynonymInLoc : getSynonyms()) {
			if (locale.equals(possibleSynonymInLoc.getLocale()))
				syns.add(possibleSynonymInLoc);
		}
		log.debug("returning: " + syns);
		return syns;
	}