public ConceptName getName(Locale locale, boolean exact) {
		
		// fail early if this concept has no names defined
		if (getNames().size() == 0) {
			if (log.isDebugEnabled())
				log.debug("there are no names defined for: " + conceptId);
			return null;
		}
		
		if (log.isDebugEnabled())
			log.debug("Getting conceptName for locale: " + locale);
		
		// matches on any name in the current locale, or first name available
		ConceptName bestName = getBestName(locale);
		
		if (exact && bestName.getLocale() != locale)
			return null; // no exact match found
		else
			return bestName;
	}