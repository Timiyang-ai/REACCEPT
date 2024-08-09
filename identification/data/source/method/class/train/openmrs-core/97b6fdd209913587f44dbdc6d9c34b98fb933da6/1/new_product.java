@SuppressWarnings({ "rawtypes" })
	@Override
	public List<ConceptSearchResult> getConcepts(String phrase, List<Locale> locales, boolean includeRetired,
	                                             List<ConceptClass> requireClasses, List<ConceptClass> excludeClasses,
	                                             List<ConceptDatatype> requireDatatypes,
	                                             List<ConceptDatatype> excludeDatatypes, Concept answersToConcept,
	                                             Integer start, Integer size) throws DAOException {
		if (StringUtils.isBlank(phrase)) {
			phrase = "%"; // match all
		}
		
		Criteria searchCriteria = createConceptWordSearchCriteria(phrase, locales, includeRetired, requireClasses,
		    excludeClasses, requireDatatypes, excludeDatatypes, answersToConcept);
		
		List<ConceptSearchResult> results = new Vector<ConceptSearchResult>();
		
		if (searchCriteria != null) {
			ProjectionList pl = Projections.projectionList();
			pl.add(Projections.distinct(Projections.groupProperty("cw1.concept")));
			pl.add(Projections.groupProperty("cw1.word"));
			//if we have multiple words for the same concept, get the one with a highest weight
			pl.add(Projections.max("cw1.weight"), "maxWeight");
			//TODO In case a concept has multiple names that contains words that match the search phrase, 
			//setting this to min or max will select the concept name that was added first or last,
			//but it should actually be the one that contains the word with the highest weight.
			//see ConceptServiceTest.getConcepts_shouldReturnASearchResultWhoseConceptNameContainsAWordWithMoreWeight()
			pl.add(Projections.min("cw1.conceptName"));
			searchCriteria.setProjection(pl);
			searchCriteria.addOrder(Order.desc("maxWeight"));
			
			if (start != null)
				searchCriteria.setFirstResult(start);
			if (size != null && size > 0)
				searchCriteria.setMaxResults(size);
			
			searchCriteria.setResultTransformer(Transformers.TO_LIST);
			List resultObjects = searchCriteria.list();
			
			for (Object obj : resultObjects) {
				List list = (List) obj;
				results.add(new ConceptSearchResult((String) list.get(1), (Concept) list.get(0), (ConceptName) list.get(3),
				        (Double) list.get(2)));
			}
		}
		
		return results;
	}