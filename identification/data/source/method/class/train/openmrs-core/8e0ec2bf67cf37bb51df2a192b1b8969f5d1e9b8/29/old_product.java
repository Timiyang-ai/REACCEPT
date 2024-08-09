public Collection<ConceptName> getNames(Locale locale) {
		Collection<ConceptName> localeNames = new Vector<ConceptName>();
		for (ConceptName possibleName : getNames()) {
			if (possibleName.getLocale().equals(locale)) {
				localeNames.add(possibleName);
			}
		}
		return localeNames;
	}