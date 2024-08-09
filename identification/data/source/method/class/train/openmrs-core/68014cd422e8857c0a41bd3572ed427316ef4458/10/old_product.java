public boolean removeConceptMapping(ConceptMap conceptMap) {
		if (conceptMappings != null)
			return conceptMappings.remove(conceptMap);
		else
			return false;
	}