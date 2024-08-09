public void addAnswer(ConceptAnswer conceptAnswer) {
		conceptAnswer.setConcept(this);
		if (answers == null)
			answers = new LinkedList<ConceptAnswer>();
		if (!answers.contains(conceptAnswer) && conceptAnswer != null)
			answers.add(conceptAnswer);
	}