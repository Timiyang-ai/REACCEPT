public void setShortName(Locale locale, ConceptName shortName) {
		ConceptNameTag shortLanguage = ConceptNameTag.shortLanguageTagFor(locale);
		ConceptNameTag shortCountry = ConceptNameTag.shortCountryTagFor(locale);
		
		ConceptName currentShortNameInLanguage = getShortNameInLanguage(locale.getLanguage());
		if (shortCountry != null) {
			if (currentShortNameInLanguage == null) {
				shortName.addTag(shortLanguage);
			}
			
			ConceptName currentPreferredForCountry = getPreferredNameForCountry(locale.getCountry());
			if (currentPreferredForCountry != null) {
				currentPreferredForCountry.removeTag(shortCountry);
			}
			shortName.addTag(shortCountry);
		} else {
			if (currentShortNameInLanguage != null) {
				currentShortNameInLanguage.removeTag(shortLanguage);
			}
			shortName.addTag(shortLanguage);
		}
		
		addName(shortName);
	}