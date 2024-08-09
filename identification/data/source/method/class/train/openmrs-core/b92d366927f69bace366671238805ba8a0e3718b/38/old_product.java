public ConceptName getName(Locale locale, boolean exact) {
		String loc = locale.getLanguage().substring(0, 2);
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
		if (exact) return null;
		
//		returning default name locale ("en") if exact match desired
		return defaultName;
	}