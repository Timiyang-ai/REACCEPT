public void addConceptReferenceTermMap(ConceptReferenceTermMap conceptReferenceTermMap) {
		if (conceptReferenceTermMap != null) {
			//can't map a term to itself
			if (conceptReferenceTermMap.getTermB() != null && !this.equals(conceptReferenceTermMap.getTermB())) {
				conceptReferenceTermMap.setTermA(this);
				if (conceptReferenceTermMaps == null)
					conceptReferenceTermMaps = new LinkedHashSet<ConceptReferenceTermMap>();
				if (!conceptReferenceTermMaps.contains(conceptReferenceTermMap))
					conceptReferenceTermMaps.add(conceptReferenceTermMap);
			}
		}
	}