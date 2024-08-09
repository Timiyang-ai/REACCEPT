public void setPreferredName(ConceptName preferredName) {
		
		if (preferredName == null || preferredName.getVoided() || preferredName.isIndexTerm()) {
			throw new APIException("Concept.error.preferredName.null", (Object[]) null);
		} else if (preferredName.getLocale() == null) {
			throw new APIException("Concept.name.locale.null", (Object[]) null);
		}
		
		//first revert the current preferred name(if any) from being preferred
		ConceptName oldPreferredName = getPreferredName(preferredName.getLocale());
		if (oldPreferredName != null) {
			oldPreferredName.setLocalePreferred(false);
		}
		
		preferredName.setLocalePreferred(true);
		//add this name, if it is new or not among this concept's names
		if (preferredName.getConceptNameId() == null || !getNames().contains(preferredName)) {
			addName(preferredName);
		}
	}