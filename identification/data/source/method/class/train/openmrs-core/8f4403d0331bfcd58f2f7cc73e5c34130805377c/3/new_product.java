public ConceptName getShortNameInLocale(Locale locale) {
		ConceptName bestMatch = null;
		if (locale != null && !getShortNames().isEmpty()) {
			for (ConceptName shortName : getShortNames()) {
				Locale nameLocale = shortName.getLocale();
				if (nameLocale.equals(locale)) {
					return shortName;
				}
				// test for partially locale match - any language matches takes precedence over country matches.
				if (OpenmrsUtil.nullSafeEquals(locale.getLanguage(), nameLocale.getLanguage())) {
					bestMatch = shortName;
				} else if (bestMatch == null && StringUtils.isNotBlank(locale.getCountry())
				        && locale.getCountry().equals(nameLocale.getCountry())) {
					bestMatch = shortName;
				}
			}
		}
		return bestMatch;
	}