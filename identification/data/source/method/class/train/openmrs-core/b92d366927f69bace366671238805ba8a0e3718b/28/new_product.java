public ConceptName getName(Locale locale) {
		log.debug("getting name corresponding to: " + locale.getLanguage());
		String loc = locale.getLanguage();
		for (Iterator<ConceptName> i = getNames().iterator(); i.hasNext();) {
			ConceptName name = i.next();
			String lang = name.getLocale();
			log.debug("name's lang: " + lang);
			if (lang.equals(loc))
				return name;
		}
		
		//no name with the given locale was found.  returning null
		return null;
	}