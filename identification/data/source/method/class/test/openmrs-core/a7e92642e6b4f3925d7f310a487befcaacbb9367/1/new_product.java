public boolean hasName(String name, Locale locale) {
		if (name == null) {
			return false;
		}
		
		Collection<ConceptName> currentNames;
		if (locale == null) {
			currentNames = getNames();
		} else {
			currentNames = getNames(locale);
		}
		
		for (ConceptName currentName : currentNames) {
			if (name.equalsIgnoreCase(currentName.getName())) {
				return true;
			}
		}
		
		return false;
	}