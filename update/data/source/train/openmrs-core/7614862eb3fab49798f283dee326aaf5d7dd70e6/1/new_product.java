public ConceptName getName(Locale locale, boolean exact) {
		
		// fail early if this concept has no names defined
		if (getNames().size() == 0) {
			if (log.isDebugEnabled())
				log.debug("there are no names defined for: " + conceptId);
			return null;
		}
		
		if (log.isDebugEnabled())
			log.debug("Getting conceptName for locale: " + locale);
		if (exact && locale != null) {
			ConceptName preferredName = getPreferredName(locale);
			if (preferredName != null)
				return preferredName;
			
			ConceptName fullySpecifiedName = getFullySpecifiedName(locale);
			if (fullySpecifiedName != null)
				return fullySpecifiedName;
			else if (getSynonyms(locale).size() > 0)
				return getSynonyms(locale).iterator().next();
			
			return null;
			
		} else {
			//just get any name
			return getName();
		}
	}