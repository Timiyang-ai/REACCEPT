public Collection<ConceptAnswer> getAnswers() {
		if (answers == null) {
			answers = new HashSet<>();
		}
		return answers;
	}