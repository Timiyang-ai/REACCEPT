public Collection<ConceptName> getNames(Locale locale) {
		Collection<ConceptName> localeNames = new Vector<ConceptName>();
		for (ConceptName possibleName : names) {
			if (possibleName.getLocale().equals(locale)) {
				localeNames.add(possibleName);
			}
		}
		return localeNames;
	}