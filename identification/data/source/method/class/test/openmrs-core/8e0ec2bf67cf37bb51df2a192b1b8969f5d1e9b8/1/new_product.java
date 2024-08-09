public Collection<ConceptName> getSynonyms(Locale locale) {
		
		List<ConceptName> syns = new ArrayList<>();
		ConceptName preferredConceptName = null;
		for (ConceptName possibleSynonymInLoc : getSynonyms()) {
			if (locale.equals(possibleSynonymInLoc.getLocale())) {
				if (possibleSynonymInLoc.isPreferred()) {
					preferredConceptName = possibleSynonymInLoc;
				} else {
					syns.add(possibleSynonymInLoc);
				}
			}
		}
		
		// Add preferred name first in the list.
		if (preferredConceptName != null) {
			syns.add(0, preferredConceptName);
		}
		log.debug("returning: " + syns);
		return syns;
	}