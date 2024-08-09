public ConceptName getPreferredName(Locale forLocale) {
		
		if (log.isDebugEnabled())
			log.debug("Getting preferred conceptName for locale: " + forLocale);
		// fail early if this concept has no names defined
		if (getNames(forLocale).size() == 0) {
			if (log.isDebugEnabled())
				log.debug("there are no names defined for concept with id: " + conceptId + " in the  locale: " + forLocale);
			return null;
		} else if (forLocale == null) {
			log.warn("Locale cannot be null");
			return null;
		}
		
		for (ConceptName nameInLocale : getNames(forLocale)) {
			if (nameInLocale.isLocalePreferred())
				return nameInLocale;
		}
		
		return null;
	}