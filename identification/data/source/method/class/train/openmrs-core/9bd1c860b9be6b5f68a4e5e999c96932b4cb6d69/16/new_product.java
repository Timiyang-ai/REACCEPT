public void addAnswer(ConceptAnswer conceptAnswer) {
		if (conceptAnswer != null) {
			if (answers == null) {
				answers = new HashSet<ConceptAnswer>();
		conceptAnswer.setConcept(this);
				answers.add(conceptAnswer);
			} else if (!answers.contains(conceptAnswer)) {
			conceptAnswer.setConcept(this);
			answers.add(conceptAnswer);
		}
	}
	}