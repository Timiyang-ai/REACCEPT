public ConceptName getBestShortName(Locale locale) {
		
		// fail early if this concept has no names defined
		if (getNames().size() == 0) {
			if (log.isDebugEnabled())
				log.debug("there are no names defined for: " + conceptId);
			return null;
		}
		
		if (log.isDebugEnabled())
			log.debug("Getting short conceptName for locale: " + locale);
		
		ConceptName bestMatch = null;
		
		if (locale == null)
			locale = Context.getLocale(); // Don't presume en_US;
			
		ConceptNameTag desiredLanguageTag = ConceptNameTag.shortLanguageTagFor(locale);
		ConceptNameTag desiredCountryTag = ConceptNameTag.shortCountryTagFor(locale);
		
		List<ConceptName> compatibleNames = getCompatibleNames(locale);
		
		if (compatibleNames.size() == 0) {
			// no compatible names, so return first available name
			Iterator<ConceptName> nameIt = getNames().iterator();
			bestMatch = nameIt.next();
		} else if (compatibleNames.size() == 1) {
			// only 1? it must be the best
			bestMatch = compatibleNames.get(0);
		} else {
			for (ConceptName possibleName : getCompatibleNames(locale)) {
				if (desiredCountryTag != null) {
					// country was specified, exact match must be preferred in country
					if (possibleName.hasTag(desiredCountryTag)) {
						bestMatch = possibleName;
						break;
					} else if (possibleName.hasTag(desiredLanguageTag)) {
						bestMatch = possibleName;
					} else if (possibleName.isShort()) {
						bestMatch = possibleName;
					} else if (bestMatch == null) {
						bestMatch = possibleName;
					}
				} else {
					// no country specified, so only worry about matching language
					if (possibleName.hasTag(desiredLanguageTag)) {
						bestMatch = possibleName;
						break;
					} else if (bestMatch.isShort()) {
						bestMatch = possibleName;
					} else if (bestMatch == null) {
						bestMatch = possibleName;
					}
				}
			}
		}
		
		if (bestMatch == null) {
			log.info("No compatible concept name found for default locale for concept id " + conceptId);
		}
		
		return bestMatch;
		
	}