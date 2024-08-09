public void setShortName(ConceptName shortName) {
		if (shortName != null) {
			if (shortName.getLocale() == null) {
				throw new APIException(CONCEPT_NAME_LOCALE_NULL, (Object[]) null);
			}
			ConceptName oldShortName = getShortNameInLocale(shortName.getLocale());
			if (oldShortName != null) {
				oldShortName.setConceptNameType(null);
			}
			shortName.setConceptNameType(ConceptNameType.SHORT);
			if (StringUtils.isNotBlank(shortName.getName())
			        && (shortName.getConceptNameId() == null || !getNames().contains(shortName))) {
				//add this name, if it is new or not among this concept's names
				addName(shortName);
			}
		} else {
			throw new APIException("Concept.error.shortName.null", (Object[]) null);
		}
	}