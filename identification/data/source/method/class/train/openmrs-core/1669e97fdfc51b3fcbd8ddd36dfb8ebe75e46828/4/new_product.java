public ConceptName getPreferredName(Locale forLocale) {
		// fail early if this concept has no names defined
		if (getNames().size() == 0) {
			if (log.isDebugEnabled())
				log.debug("there are no names defined for: " + conceptId);
			return null;
		}
		
		if (log.isDebugEnabled())
			log.debug("Getting preferred conceptName for locale: " + forLocale);
		
		ConceptName preferredName = null; // name which exactly match the locale
		// and is preferred
		if (forLocale == null)
			forLocale = Context.getLocale(); // Don't presume en_US;
			
		ConceptNameTag desiredLanguageTag = ConceptNameTag.preferredLanguageTagFor(forLocale);
		ConceptNameTag desiredCountryTag = ConceptNameTag.preferredCountryTagFor(forLocale);
		
		for (ConceptName possibleName : getCompatibleNames(forLocale)) {
			if (forLocale.equals(possibleName.getLocale()) && possibleName.hasTag(ConceptNameTag.PREFERRED)) {
				preferredName = possibleName;
				break;
			}
			if (desiredCountryTag != null) {
				// country was specified, exact match must be preferred in country
				if (possibleName.hasTag(desiredCountryTag)) {
					preferredName = possibleName;
					break;
				}
			} else {
				// no country specified, so only worry about matching language
				if (possibleName.hasTag(desiredLanguageTag)) {
					preferredName = possibleName;
					break;
				}
			}
			if ((preferredName == null) && possibleName.hasTag(ConceptNameTag.PREFERRED)) {
				preferredName = possibleName;
			}
		}
		
		if (log.isDebugEnabled()) {
			if (preferredName == null) {
				log.warn("No preferred concept name found for concept id " + conceptId + " in locale " + forLocale);
			}
		}
		
		return preferredName;
	}