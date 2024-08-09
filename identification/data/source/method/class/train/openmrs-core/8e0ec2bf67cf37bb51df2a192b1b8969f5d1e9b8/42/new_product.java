public Collection<ConceptAnswer> getAnswers() {
		if (answers == null) {
			answers = new HashSet<ConceptAnswer>();
		}
		return answers;
	}