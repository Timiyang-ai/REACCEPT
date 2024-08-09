public void setPreferredName(ConceptName preferredName) {

		if (preferredName == null || preferredName.isVoided() || preferredName.isIndexTerm()) {
			throw new APIException("Preferred name cannot be null, voided or an index term");
		} else if (preferredName.getLocale() == null) {
			throw new APIException("The locale for a concept name cannot be null");
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