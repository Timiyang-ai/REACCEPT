public Set<Locale> getAllConceptNameLocales() {
		if (getNames().isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug("The Concept with id: " + conceptId + " has no names");
			}
			return null;
		}
		
		Set<Locale> locales = new HashSet<Locale>();
		
		for (ConceptName cn : getNames()) {
			locales.add(cn.getLocale());
		}
		
		return locales;
	}