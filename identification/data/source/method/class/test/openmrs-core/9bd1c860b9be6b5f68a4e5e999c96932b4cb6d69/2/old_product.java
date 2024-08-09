public boolean addName(ConceptName conceptName) {
		boolean nameListWasModified = false;
		conceptName.setConcept(this);
		if (names == null)
			names = new HashSet<ConceptName>();
		if (!names.contains(conceptName) && conceptName != null) {
			nameListWasModified = names.add(conceptName);
			if (compatibleCache != null) {
				compatibleCache.clear(); // clear the locale cache, forcing it to be rebuilt
			}
		} 
		return nameListWasModified;
	}