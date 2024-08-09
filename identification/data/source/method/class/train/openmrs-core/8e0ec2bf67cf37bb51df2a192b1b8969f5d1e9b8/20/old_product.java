@ElementList
	public Collection<ConceptAnswer> getAnswers() {
		Collection<ConceptAnswer> newAnswers = new HashSet<ConceptAnswer>();
		for (ConceptAnswer ca : answers) {
			if (!ca.getAnswerConcept().isRetired())
				newAnswers.add(ca);
		}
		return newAnswers;
	}