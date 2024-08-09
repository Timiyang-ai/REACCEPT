public ConceptName getName(Locale locale) {
		String loc = locale.getLanguage();
		for (Iterator<ConceptName> i = getNames().iterator(); i.hasNext();) {
			ConceptName name = i.next();
			String lang = name.getLocale();
			if (lang.equals(loc))
				return name;
		}
		
		//no name with the given locale was found.  returning null
		return null;
	}