public boolean equals(Object object) {
		if (!(object instanceof ConceptDescription)) {
			return false;
		}
		ConceptDescription rhs = (ConceptDescription) object;
		if (conceptDescriptionId != null && rhs.conceptDescriptionId != null)
			return this.conceptDescriptionId == rhs.conceptDescriptionId;
		else
			return this == object;
	}