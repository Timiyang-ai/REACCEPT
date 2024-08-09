public boolean removeDescription(ConceptDescription description) {
		if (descriptions != null)
			return descriptions.remove(description);
		else
			return false;
	}