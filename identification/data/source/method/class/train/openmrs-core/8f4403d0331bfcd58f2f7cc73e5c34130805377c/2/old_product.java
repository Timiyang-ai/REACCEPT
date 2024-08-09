public ConceptName getShortNameInLocale(Locale locale) {
		if (locale != null && getShortNames().size() > 0) {
			for (ConceptName shortName : getShortNames()) {
				if (shortName.getLocale().equals(locale))
					return shortName;
			}
		}
		return null;
	}