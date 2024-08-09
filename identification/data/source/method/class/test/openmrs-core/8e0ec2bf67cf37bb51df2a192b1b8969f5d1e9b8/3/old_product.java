public Collection<ConceptAnswer> getAnswers(boolean includeRetired) {
		if (includeRetired == false)
			return getAnswers();
		return answers;
	}