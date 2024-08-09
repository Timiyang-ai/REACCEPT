public ConceptName getName(Locale locale) {
		
		for (Iterator<ConceptName> i = getNames().iterator(); i.hasNext();) {
			ConceptName name = i.next();
			if (name.getLocale().equals(locale))
				return name;
		}
		
		//no name with the given locale was found.  returning null
		return null;
	}