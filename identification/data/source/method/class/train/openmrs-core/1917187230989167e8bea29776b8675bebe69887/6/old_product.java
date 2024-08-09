public void setFullySpecifiedName(ConceptName fullySpecifiedName) {
		if (fullySpecifiedName == null || fullySpecifiedName.getLocale() == null) {
			throw new APIException("Concept.name.locale.null", (Object[]) null);
		} else if (fullySpecifiedName.isVoided()) {
			throw new APIException("Concept.error.fullySpecifiedName.null", (Object[]) null);
		}
		
		ConceptName oldFullySpecifiedName = getFullySpecifiedName(fullySpecifiedName.getLocale());
		if (oldFullySpecifiedName != null) {
			oldFullySpecifiedName.setConceptNameType(null);
		}
		fullySpecifiedName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
		//add this name, if it is new or not among this concept's names
		if (fullySpecifiedName.getConceptNameId() == null || !getNames().contains(fullySpecifiedName)) {
			addName(fullySpecifiedName);
		}
	}