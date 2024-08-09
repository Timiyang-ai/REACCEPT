public ConceptName getName(Locale locale) {
		
		for (Iterator<ConceptName> i = getNames().iterator(); i.hasNext();) {
			ConceptName name = i.next();
			String lang = name.getLocale();
			if (lang.equals(locale.getLanguage()))
				return name;
		}
		
		//no name with the given locale was found.  returning null
		return null;
	}