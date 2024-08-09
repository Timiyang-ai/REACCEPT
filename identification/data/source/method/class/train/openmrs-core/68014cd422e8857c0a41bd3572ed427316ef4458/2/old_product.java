public boolean removeDescription(ConceptDescription description) {
		if (getDescriptions() != null)
			return descriptions.remove(description);
		else
			return false;
	}