public ConceptName getFullySpecifiedName(Locale locale) {
		if (locale != null && getNames(locale).size() > 0) {
			//get the first fully specified name, since every concept must have a fully specified name,
			//then, this loop will have to return a name
			for (ConceptName conceptName : getNames(locale)) {
				if (ObjectUtils.nullSafeEquals(conceptName.isFullySpecifiedName(), true)) {
					return conceptName;
				}
			}
			
			// look for partially locale match - any language matches takes precedence over country matches.
			ConceptName bestMatch = null;
			for (ConceptName conceptName : getPartiallyCompatibleNames(locale)) {
				if (ObjectUtils.nullSafeEquals(conceptName.isFullySpecifiedName(), true)) {
					Locale nameLocale = conceptName.getLocale();
					if (locale.getLanguage().equals(nameLocale.getLanguage())) {
						return conceptName;
					}
					bestMatch = conceptName;
				}
			}
			return bestMatch;
			
		}
		return null;
	}