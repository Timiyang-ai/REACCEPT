@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		// now do the standard equals comparison
		if (obj instanceof ConceptNumeric) {
			ConceptNumeric c = (ConceptNumeric) obj;
			return OpenmrsUtil.nullSafeEquals(this.getConceptId(), c.getConceptId());
		} else if (obj instanceof Concept) {
			// use the reverse .equals in case we have hibernate proxies - #1511
			return OpenmrsUtil.nullSafeEquals(((Concept) obj).getConceptId(), this.getConceptId());
		}
		return obj == this;
	}