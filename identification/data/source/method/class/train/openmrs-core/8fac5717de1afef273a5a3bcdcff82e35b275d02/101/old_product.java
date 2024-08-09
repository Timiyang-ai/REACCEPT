public List<Drug> getDrugs(Concept concept, boolean includeRetired) {
		if (includeRetired == true)
			throw new APIException("Getting retired drugs is no longer an options.  Use the getAllDrugs() method for that");
		
		return getDrugsByConcept(concept);
	}