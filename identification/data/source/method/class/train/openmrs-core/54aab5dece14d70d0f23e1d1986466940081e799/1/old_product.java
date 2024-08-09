public boolean equals(Object obj) {
		if (obj instanceof ConceptNumeric) {
			ConceptNumeric c = (ConceptNumeric) obj;
			return (this.getConceptId().equals(c.getConceptId()));
		}
		return false;
	}