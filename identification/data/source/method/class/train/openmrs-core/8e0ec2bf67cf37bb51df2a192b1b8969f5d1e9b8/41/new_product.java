public Collection<ConceptName> getSynonyms(Locale locale) {
		String desiredLanguage = locale.getLanguage();
		Collection<ConceptName> syns = new Vector<ConceptName>();
		for (ConceptName possibleSynonym : getNames()) {
			if (possibleSynonym.hasTag(ConceptNameTag.SYNONYM)) {
				String lang = possibleSynonym.getLocale().getLanguage();
				if (lang.equals(desiredLanguage))
					syns.add(possibleSynonym);
			}
		}
		log.debug("returning: " + syns);
		return syns;
	}