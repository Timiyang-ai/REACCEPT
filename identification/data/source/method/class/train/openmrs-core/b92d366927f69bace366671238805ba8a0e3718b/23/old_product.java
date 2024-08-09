public ConceptName getName(Locale locale, boolean exact) {
		
		// fail early if this concept has no names defined
		if (getNames().size() == 0) {
			if (log.isDebugEnabled())
				log.debug("there are no names defined for: " + conceptId);
			return null;
		}
		
		if (log.isDebugEnabled())
			log.debug("Getting conceptName for locale: " + locale);
		
		ConceptName exactName = getNameInLocale(locale);
		
		if (exactName != null || exact) {
			return exactName;
		}
		
		//just get any name
		return getName();
	}