public void addName(ConceptName conceptName) {
		if (conceptName != null) {
			conceptName.setConcept(this);
			if (names == null) {
				names = new HashSet<>();
			}
			if (!names.contains(conceptName)) {
				if (getNames().isEmpty()
				        && !ConceptNameType.FULLY_SPECIFIED.equals(conceptName.getConceptNameType())) {
					conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				} else {
					if (conceptName.isPreferred() && !conceptName.isIndexTerm() && conceptName.getLocale() != null) {
						ConceptName prefName = getPreferredName(conceptName.getLocale());
						if (prefName != null) {
							prefName.setLocalePreferred(false);
						}
					}
					if (conceptName.isFullySpecifiedName() && conceptName.getLocale() != null) {
						ConceptName fullySpecName = getFullySpecifiedName(conceptName.getLocale());
						if (fullySpecName != null) {
							fullySpecName.setConceptNameType(null);
						}
					} else if (conceptName.isShort() && conceptName.getLocale() != null) {
						ConceptName shortName = getShortNameInLocale(conceptName.getLocale());
						if (shortName != null) {
							shortName.setConceptNameType(null);
						}
					}
				}
				names.add(conceptName);
				if (compatibleCache != null) {
					compatibleCache.clear(); // clear the locale cache, forcing it to be rebuilt
				}
			}
		}
	}