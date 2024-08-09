public ConceptName getFullySpecifiedName(Locale locale) {
		if (locale != null && getNames(locale).size() > 0) {
			//get the first fully specified name, since every concept must have a fully specified name,
			//then, this loop will have to return a name
			for (ConceptName conceptName : getNames(locale)) {
				if (ObjectUtils.nullSafeEquals(conceptName.isFullySpecifiedName(), true)) {
					return conceptName;
				}
			}
		}
		return null;
	}