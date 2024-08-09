public void addAnswer(ConceptAnswer conceptAnswer) {
		conceptAnswer.setConcept(this);
		if (answers == null)
			answers = new HashSet<ConceptAnswer>();
		if (!answers.contains(conceptAnswer) && conceptAnswer != null)
		{
			conceptAnswer.setConcept(this);
			answers.add(conceptAnswer);
		}
	}