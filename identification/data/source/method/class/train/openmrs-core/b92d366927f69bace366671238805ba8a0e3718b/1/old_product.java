public ConceptName getName(Locale locale, boolean exact) {
		
		// fail early if this concept has no names defined
		if (names == null || names.size() == 0) {
			if (log.isDebugEnabled())
				log.debug("there are no names defined for: " + conceptId);
			return null;
		}
		
		if (log.isDebugEnabled())
			log.debug("Getting conceptName for locale: " + locale);
		
		ConceptName exactMatch = null; // name which exactly match the locale
		// and is preferred
		ConceptName bestMatch = null; // name from compatible locale, may not
		// be preferred
		
		if (locale == null)
			locale = Context.getLocale(); // Don't presume en_US;
			
		ConceptNameTag desiredLanguageTag = ConceptNameTag.preferredLanguageTagFor(locale);
		ConceptNameTag desiredCountryTag = ConceptNameTag.preferredCountryTagFor(locale);
		
		for (ConceptName possibleName : getCompatibleNames(locale)) {
			if (locale.equals(possibleName.getLocale()) && possibleName.hasTag(ConceptNameTag.PREFERRED)) {
				exactMatch = possibleName;
				break;
			}
			if (desiredCountryTag != null) {
				// country was specified, exact match must be preferred in country
				if (possibleName.hasTag(desiredCountryTag)) {
					exactMatch = possibleName;
					break;
				} else if (possibleName.hasTag(desiredLanguageTag)) {
					bestMatch = possibleName;
				} else if (possibleName.hasTag(ConceptNameTag.PREFERRED)) {
					bestMatch = possibleName;
				} else if (bestMatch == null) { // ABK: verbose, but clear
					bestMatch = possibleName;
				}
			} else {
				// no country specified, so only worry about matching language
				if (possibleName.hasTag(desiredLanguageTag)) {
					exactMatch = possibleName;
					break;
				} else if (possibleName.hasTag(ConceptNameTag.PREFERRED)) {
					bestMatch = possibleName;
				} else if (bestMatch == null) {
					bestMatch = possibleName;
				}
			}
		}
		
		if (exact) {
			if (exactMatch == null)
				log.warn("No concept name found for concept id " + conceptId + " for locale " + locale.toString());
			return exactMatch;
		}
		
		if (exactMatch != null)
			return exactMatch;
		
		if (bestMatch != null)
			return bestMatch;
		
		log.warn("No compatible concept name found for default locale for concept id " + conceptId);
		
		ConceptName defaultName = null; // any available name for the concept
		
		// populate defaultName with the first concept name
		if (getNames() != null && getNames().size() > 0)
			defaultName = (ConceptName) getNames().toArray()[0];
		
		if (defaultName == null) {
			log.error("No concept names exist for concept id: " + conceptId);
		}
		
		return defaultName;
	}