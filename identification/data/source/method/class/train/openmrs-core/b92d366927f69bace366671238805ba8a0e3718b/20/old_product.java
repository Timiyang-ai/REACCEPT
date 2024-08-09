public ConceptDescription getDescription(Locale locale, boolean exact) {
		log.debug("Getting ConceptDescription for locale: " + locale);
		
		ConceptDescription foundDescription = null;
		
		if (locale == null)
			locale = LocaleUtility.DEFAULT_LOCALE;
		
		Locale desiredLocale = locale;
		
		ConceptDescription defaultDescription = null;
		for (Iterator<ConceptDescription> i = getDescriptions().iterator(); i.hasNext() && (foundDescription == null);) {
			ConceptDescription availableDescription = i.next();
			Locale availableLocale = availableDescription.getLocale();
			if (availableLocale.equals(desiredLocale))
				foundDescription = availableDescription;
			if (availableLocale.equals(LocaleUtility.DEFAULT_LOCALE))
				defaultDescription = availableDescription;
		}
		
		if (foundDescription == null) {
			// no description with the given locale was found.
			// return null if exact match desired
			if (exact) {
				log.warn("No concept description found for concept id " + conceptId + " for locale "
				        + desiredLocale.toString());
			} else {
				// returning default description locale ("en") if exact match
				// not desired
				if (defaultDescription == null)
					log.warn("No concept description found for default locale for concept id " + conceptId);
				else {
					foundDescription = defaultDescription;
				}
			}
		}
		return foundDescription;
	}