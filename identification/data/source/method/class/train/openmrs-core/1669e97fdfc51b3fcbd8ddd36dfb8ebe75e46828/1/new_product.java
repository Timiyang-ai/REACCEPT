public ConceptName getPreferredName(Locale forLocale) {
		
		if (log.isDebugEnabled()) {
			log.debug("Getting preferred conceptName for locale: " + forLocale);
		}
		// fail early if this concept has no names defined
		if (getNames(forLocale).isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug("there are no names defined for concept with id: " + conceptId + " in the  locale: " + forLocale);
			}
			return null;
		} else if (forLocale == null) {
			log.warn("Locale cannot be null");
			return null;
		}
		
		for (ConceptName nameInLocale : getNames(forLocale)) {
			if (ObjectUtils.nullSafeEquals(nameInLocale.getLocalePreferred(), true)) {
				return nameInLocale;
			}
		}
		
		// look for partially locale match - any language matches takes precedence over country matches.
		ConceptName bestMatch = null;
		
		for (ConceptName nameInLocale : getPartiallyCompatibleNames(forLocale)) {
			if (ObjectUtils.nullSafeEquals(nameInLocale.getLocalePreferred(), true)) {
				Locale nameLocale = nameInLocale.getLocale();
				if (forLocale.getLanguage().equals(nameLocale.getLanguage())) {
					return nameInLocale;
				} else {
					bestMatch = nameInLocale;
				}
				
			}
		}
		
		if (bestMatch != null) {
			return bestMatch;
		}
		
		return getFullySpecifiedName(forLocale);
	}