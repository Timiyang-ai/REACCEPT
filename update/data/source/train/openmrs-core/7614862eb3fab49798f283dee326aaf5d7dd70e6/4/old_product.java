public ConceptName getBestName(Locale locale) {
		
		// fail early if this concept has no names defined
		if (getNames().size() == 0) {
			if (log.isDebugEnabled())
				log.debug("there are no names defined for: " + conceptId);
			return null;
		}
		
		if (log.isDebugEnabled())
			log.debug("Getting conceptName for locale: " + locale);
		
		ConceptName bestMatch = null;
		
		if (locale == null)
			locale = Context.getLocale(); // Don't presume en_US;
			
		ConceptNameTag desiredLanguageTag = ConceptNameTag.preferredLanguageTagFor(locale);
		ConceptNameTag desiredCountryTag = ConceptNameTag.preferredCountryTagFor(locale);
		
		List<ConceptName> compatibleNames = getCompatibleNames(locale);
		
		if (compatibleNames.size() == 0) {
			// no compatible names, so return first available name
			Iterator<ConceptName> nameIt = getNames().iterator();
			bestMatch = nameIt.next();
		} else if (compatibleNames.size() == 1) {
			bestMatch = compatibleNames.get(0);
		} else {
			// more than 1 choice? search through to find the "best"
			for (ConceptName possibleName : compatibleNames) {
				if (locale.equals(possibleName.getLocale()) && possibleName.hasTag(ConceptNameTag.PREFERRED)) {
					bestMatch = possibleName;
					break;
				}
				if (desiredCountryTag != null) {
					// country was specified, exact match must be preferred in country
					if (possibleName.hasTag(desiredCountryTag)) {
						bestMatch = possibleName;
						break; // can't get any better than this match
					} else if (possibleName.hasTag(desiredLanguageTag)) {
						bestMatch = possibleName;
					} else if (possibleName.hasTag(ConceptNameTag.PREFERRED)) {
						bestMatch = possibleName;
					} else if (bestMatch == null) {
						bestMatch = possibleName;
					}
				} else {
					// no country specified, so only worry about matching language
					if (possibleName.hasTag(desiredLanguageTag)) {
						bestMatch = possibleName;
						break;
					} else if (possibleName.hasTag(ConceptNameTag.PREFERRED)) {
						bestMatch = possibleName;
					} else if (bestMatch == null) {
						bestMatch = possibleName;
					}
				}
			}
		}
		
		if (bestMatch == null) {
			log.warn("No compatible concept name found for for concept id " + conceptId);
		}
		
		return bestMatch;
		
	}