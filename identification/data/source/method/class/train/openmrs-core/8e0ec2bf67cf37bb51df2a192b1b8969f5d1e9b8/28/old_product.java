public Collection<ConceptName> getSynonyms(Locale locale) {
		String desiredLanguage = locale.getLanguage();
		Collection<ConceptName> syns = new Vector<ConceptName>();
		for (ConceptName possibleSynonym : names) {
			String lang = possibleSynonym.getLocale().getLanguage();
			if (lang.equals(desiredLanguage))
				syns.add(possibleSynonym);
		}
		log.debug("returning: " + syns);
		return syns;
	}