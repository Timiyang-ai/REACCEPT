public void addName(ConceptName conceptName) {
		conceptName.setConcept(this);
		if (names == null)
			names = new HashSet<ConceptName>();
		if (conceptName != null && !names.contains(conceptName)) {
			names.add(conceptName);
			if (compatibleCache != null) {
				compatibleCache.clear(); // clear the locale cache, forcing it to be rebuilt
			}
		}
	}