public void addName(ConceptName conceptName) {
		conceptName.setConcept(this);
		if (names == null)
			names = new LinkedList<ConceptName>();
		if (!names.contains(conceptName) && conceptName != null)
			names.add(conceptName);
	}