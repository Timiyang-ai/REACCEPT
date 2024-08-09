public Collection<ConceptAnswer> getAnswers(boolean includeRetired) {
		return getAnswers().stream()
				.filter(a -> includeRetired || !a.getAnswerConcept().getRetired())
				.collect(Collectors.toSet());
	}