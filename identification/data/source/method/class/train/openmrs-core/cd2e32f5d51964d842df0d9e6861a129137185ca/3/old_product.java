public void setShortName(ConceptName shortName) {
		if (shortName.getLocale() == null)
			throw new APIException("The locale for a concept name cannot be null");
		else if (shortName != null && !shortName.isVoided()) {
			ConceptName oldShortName = getShortNameInLocale(shortName.getLocale());
			if (oldShortName != null)
				oldShortName.setConceptNameType(null);
			shortName.setConceptNameType(ConceptNameType.SHORT);
			//add this name, if it is new or not among this concept's names
			if (shortName.getConceptNameId() == null || !getNames().contains(shortName))
				addName(shortName);
		} else
			throw new APIException("Short name cannot be null or voided");
	}