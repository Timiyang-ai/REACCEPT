public void addConceptReferenceTermMap(ConceptReferenceTermMap conceptReferenceTermMap) {
		if (conceptReferenceTermMap != null && conceptReferenceTermMap.getTermB() != null
		        && !this.equals(conceptReferenceTermMap.getTermB())) {
			//can't map a term to itself
			conceptReferenceTermMap.setTermA(this);
			if (conceptReferenceTermMaps == null) {
				conceptReferenceTermMaps = new LinkedHashSet<ConceptReferenceTermMap>();
			}
			if (!conceptReferenceTermMaps.contains(conceptReferenceTermMap)) {
				conceptReferenceTermMaps.add(conceptReferenceTermMap);
			}
		}
	}