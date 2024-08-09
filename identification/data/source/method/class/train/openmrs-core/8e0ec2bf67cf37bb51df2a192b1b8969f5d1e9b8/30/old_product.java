public Collection<ConceptAnswer> getAnswers(boolean includeRetired) {
		if (!includeRetired)
			return getAnswers();
		return answers;
	}