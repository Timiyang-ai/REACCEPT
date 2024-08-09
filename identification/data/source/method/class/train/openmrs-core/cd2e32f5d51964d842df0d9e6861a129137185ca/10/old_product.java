public void setPreferredName(Locale locale, ConceptName preferredName) {
		ConceptNameTag preferredLanguage = ConceptNameTag.preferredLanguageTagFor(locale);
		ConceptNameTag preferredCountry = ConceptNameTag.preferredCountryTagFor(locale);
		
		ConceptName currentPreferredNameInLanguage = getPreferredNameInLanguage(locale.getLanguage());
		if ((preferredCountry == null) && (currentPreferredNameInLanguage == null)) {
			preferredName.addTag(preferredLanguage);
		} 

		if (preferredCountry != null) {
			ConceptName currentPreferredForCountry  = getPreferredNameForCountry(locale.getCountry());
			if (currentPreferredForCountry != null) {
				currentPreferredForCountry.removeTag(preferredCountry);
			}
			preferredName.addTag(preferredCountry);
		}

		addName(preferredName);
	}