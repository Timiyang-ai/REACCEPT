public Collection<ConceptAnswer> getAnswers(boolean includeRetired) {
		if (!includeRetired) {
			Collection<ConceptAnswer> newAnswers = new HashSet<ConceptAnswer>();
			if (answers != null) {
				for (ConceptAnswer ca : answers) {
					if (!ca.getAnswerConcept().isRetired()) {
						newAnswers.add(ca);
					}
				}
			}
			return newAnswers;
		} else {
			return getAnswers();
		}
	}