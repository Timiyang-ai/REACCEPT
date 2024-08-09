@Override
    public boolean equals(Object object) {
		if (!(object instanceof ConceptDescription)) {
			return false;
		}
		ConceptDescription rhs = (ConceptDescription) object;
		if (conceptDescriptionId != null && rhs.conceptDescriptionId != null)
			return this.conceptDescriptionId.equals(rhs.conceptDescriptionId);
		else
			return this == object;
	}