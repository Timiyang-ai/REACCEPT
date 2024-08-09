public void setShortName(Locale locale, ConceptName shortName) {
		ConceptNameTag shortLanguage = ConceptNameTag.shortLanguageTagFor(locale);
		ConceptNameTag shortCountry = ConceptNameTag.shortCountryTagFor(locale);
		
		ConceptName currentShortNameInLanguage = getShortNameInLanguage(locale.getLanguage());
		if ((shortCountry == null) && (currentShortNameInLanguage == null)) {
			shortName.addTag(shortLanguage);
		} 

		if (shortCountry != null) {
			ConceptName currentPreferredForCountry  = getPreferredNameForCountry(locale.getCountry());
			if (currentPreferredForCountry != null) {
				currentPreferredForCountry.removeTag(shortCountry);
			}
			shortName.addTag(shortCountry);
		}

		addName(shortName);
	}