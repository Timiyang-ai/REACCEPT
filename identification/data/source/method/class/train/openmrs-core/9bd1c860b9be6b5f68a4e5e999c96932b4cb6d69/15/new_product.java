public void addAnswer(ConceptAnswer conceptAnswer) {
		if (conceptAnswer != null) {
			if (!getAnswers().contains(conceptAnswer)) {
				conceptAnswer.setConcept(this);
				getAnswers().add(conceptAnswer);
			}
			
			if ((conceptAnswer.getSortWeight() == null) || (conceptAnswer.getSortWeight() <= 0)) {
				//find largest sort weight
				ConceptAnswer a = Collections.max(answers);
				Double sortWeight = (a == null) ? 1d : ((a.getSortWeight() == null) ? 1d : a.getSortWeight() + 1d);//a.sortWeight can be NULL
				conceptAnswer.setSortWeight(sortWeight);
			}
		}
	}