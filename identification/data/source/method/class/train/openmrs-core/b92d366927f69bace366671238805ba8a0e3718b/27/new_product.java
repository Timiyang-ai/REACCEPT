public ConceptName getName(Locale locale, boolean exact) {
		log.debug("Getting conceptName for locale: " + locale);
		
		if (locale == null)
			locale = Locale.US;
		
		String loc = locale.getLanguage();
		if (loc.length() > 2)
			loc = loc.substring(0, 2);
		ConceptName defaultName = null;
		for (Iterator<ConceptName> i = getNames().iterator(); i.hasNext();) {
			ConceptName name = i.next();
			String lang = name.getLocale();
			if (lang.equals(loc))
				return name;
			if (lang.equals("en"))
				defaultName = name;
		}
		
		//no name with the given locale was found.
		// return null if exact match desired
		if (exact) {
			log.warn("No concept name found for concept id " + conceptId + " for locale " + loc);
			return null;
		}
		
		// returning default name locale ("en") if exact match not desired
		if (defaultName == null)
			log.warn("No concept name found for default locale for concept id " + conceptId);
		
		return defaultName;
	}