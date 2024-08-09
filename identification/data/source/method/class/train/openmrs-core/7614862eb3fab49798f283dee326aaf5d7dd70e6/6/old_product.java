public void setPreferredName(Locale locale, ConceptName preferredName) {
		ConceptNameTag preferredLanguage = ConceptNameTag.preferredLanguageTagFor(locale);
		ConceptNameTag preferredCountry = ConceptNameTag.preferredCountryTagFor(locale);
		
		ConceptName currentPreferredNameInLanguage = getPreferredNameInLanguage(locale.getLanguage());
		
		if (preferredCountry != null) {
			if (currentPreferredNameInLanguage == null) {
				preferredName.addTag(preferredLanguage);
			}
			
			ConceptName currentPreferredForCountry = getPreferredNameForCountry(locale.getCountry());
			if (currentPreferredForCountry != null) {
				currentPreferredForCountry.removeTag(preferredCountry);
			}
			preferredName.addTag(preferredCountry);
		} else {
			if (currentPreferredNameInLanguage != null) {
				currentPreferredNameInLanguage.removeTag(preferredLanguage);
			}
			preferredName.addTag(preferredLanguage);
		}
		
		addName(preferredName);
	}