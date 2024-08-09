public ConceptName getShortNameInLocale(Locale locale) {
		ConceptName shortName = null;
		// ABK: country will always be non-null. Empty string (instead
		// of null) indicates no country was specified
		String country = locale.getCountry();
		if (country.length() != 0) {
			shortName = getShortNameForCountry(country);
		} else {
			shortName = getShortNameInLanguage(locale.getLanguage());
		}
		// default to getting the name in the specific locale tagged as "short"
		if (shortName == null) {
			for (ConceptName name : getCompatibleNames(locale)) {
				if (name.hasTag(ConceptNameTag.SHORT))
					return name;
			}
		}
		return shortName;
	}