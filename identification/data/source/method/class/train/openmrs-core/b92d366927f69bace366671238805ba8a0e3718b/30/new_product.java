public ConceptDescription getDescription(Locale locale, boolean exact) {
		log.debug("Getting ConceptDescription for locale: " + locale);
		
		ConceptDescription foundDescription = null;
		
		if (locale == null)
			locale = LocaleUtility.getDefaultLocale();
		
		Locale desiredLocale = locale;
		
		ConceptDescription defaultDescription = null;
		for (Iterator<ConceptDescription> i = getDescriptions().iterator(); i.hasNext();) {
			ConceptDescription availableDescription = i.next();
			Locale availableLocale = availableDescription.getLocale();
			if (availableLocale.equals(desiredLocale)) {
				foundDescription = availableDescription;
				break; // skip out now because we found an exact locale match
			}
			if (!exact && LocaleUtility.areCompatible(availableLocale, desiredLocale))
				foundDescription = availableDescription;
			if (availableLocale.equals(LocaleUtility.getDefaultLocale()))
				defaultDescription = availableDescription;
		}
		
		if (foundDescription == null) {
			// no description with the given locale was found.
			// return null if exact match desired
			if (exact) {
				log.debug("No concept description found for concept id " + conceptId + " for locale "
				        + desiredLocale.toString());
			} else {
				// returning default description locale ("en") if exact match
				// not desired
				if (defaultDescription == null)
					log.debug("No concept description found for default locale for concept id " + conceptId);
				else {
					foundDescription = defaultDescription;
				}
			}
		}
		return foundDescription;
	}