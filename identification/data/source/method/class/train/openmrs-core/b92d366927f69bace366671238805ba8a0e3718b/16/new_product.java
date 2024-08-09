public ConceptName getName(Locale locale, boolean exact) {
		
		// fail early if this concept has no names defined
		if (getNames() == null || getNames().size() == 0) {
			log.debug("there are no names defined for: " + conceptId);
			return null;
		}
		
		log.debug("Getting conceptName for locale: " + locale);

		ConceptName bestMatch = null; // name from compatible locale (not exactly exact)
		ConceptName defaultName = null; // any available name for the concept

		if (locale == null)
			locale = Context.getLocale(); // Don't presume en_US;

		String desiredLanguage = locale.getLanguage();
		//String desiredCountry = locale.getCountry();
		
		for (Iterator<ConceptName> i = getNames().iterator(); i.hasNext();) {
			ConceptName possibleName = i.next();

			if (possibleName.getLocale().equals(locale)) {
				bestMatch = possibleName;
				break;
			} else {
				if (defaultName == null)
					defaultName = possibleName;
				if (bestMatch == null) {
					if (possibleName.getLocale()
					                .getLanguage()
					                .equals(desiredLanguage)) {
						bestMatch = possibleName;
					}
				}
			}
		}

		if (exact) {
			if (bestMatch == null)
				log.warn("No concept name found for concept id " + conceptId
				        + " for locale " + locale.toString());
			return bestMatch;
		}

		if (bestMatch != null)
			return bestMatch;

		log.warn("No compatible concept name found for default locale for concept id "
		        + conceptId);

		if (defaultName == null) {
			log.error("No concept names exist for concept id: " + conceptId);
		}

		return defaultName;
	}