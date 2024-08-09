@ElementList
	public Collection<ConceptAnswer> getAnswers() {
		return (answers != null) ? answers : new HashSet<ConceptAnswer>();
		
	}