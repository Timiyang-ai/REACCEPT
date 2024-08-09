public boolean removeConceptMapping(ConceptMap conceptMap) {
		if (getConceptMappings() != null)
			return conceptMappings.remove(conceptMap);
		else
			return false;
	}