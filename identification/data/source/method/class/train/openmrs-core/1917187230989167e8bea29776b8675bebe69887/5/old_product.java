public void setFullySpecifiedName(ConceptName fullySpecifiedName) {
		if (fullySpecifiedName.getLocale() == null)
			throw new APIException("The locale for a concept name cannot be null");
		else if (fullySpecifiedName != null && !fullySpecifiedName.isVoided()) {
			ConceptName oldFullySpecifiedName = getFullySpecifiedName(fullySpecifiedName.getLocale());
			if (oldFullySpecifiedName != null)
				oldFullySpecifiedName.setConceptNameType(null);
			fullySpecifiedName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
			//add this name, if it is new or not among this concept's names
			if (fullySpecifiedName.getConceptNameId() == null || !getNames().contains(fullySpecifiedName))
				addName(fullySpecifiedName);
		} else
			throw new APIException("Fully Specified name cannot be null or voided");
	}