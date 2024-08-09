public boolean addName(ConceptName conceptName) {
		boolean nameListWasModified = false;
		conceptName.setConcept(this);
		if (getNames() == null)
			names = new HashSet<ConceptName>();
		if (conceptName != null && !names.contains(conceptName)) {
			nameListWasModified = names.add(conceptName);
			if (compatibleCache != null) {
				compatibleCache.clear(); // clear the locale cache, forcing it to be rebuilt
			}
		} 
		return nameListWasModified;
	}