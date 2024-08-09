public void setPreferredName(ConceptName preferredName) {
		
		if (preferredName.getLocale() == null)
			throw new APIException("The locale for a concept name cannot be null");
		else if (preferredName != null && !preferredName.isVoided() && !preferredName.isIndexTerm()) {
			//first revert the current preferred name(if any) from being preferred
			ConceptName oldPreferredName = getPreferredName(preferredName.getLocale());
			if (oldPreferredName != null)
				oldPreferredName.setLocalePreferred(false);
			
			preferredName.setLocalePreferred(true);
			//add this name, if it is new or not among this concept's names
			if (preferredName.getConceptNameId() == null || !getNames().contains(preferredName))
				addName(preferredName);
		} else
			throw new APIException("Preferred name cannot be null, voided or an index term");
	}