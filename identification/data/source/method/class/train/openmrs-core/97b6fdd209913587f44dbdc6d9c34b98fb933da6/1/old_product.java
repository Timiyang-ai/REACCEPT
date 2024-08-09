@SuppressWarnings( { "rawtypes" })
	@Override
	public List<ConceptSearchResult> getConcepts(String phrase, List<Locale> locales, boolean includeRetired,
	        List<ConceptClass> requireClasses, List<ConceptClass> excludeClasses, List<ConceptDatatype> requireDatatypes,
	        List<ConceptDatatype> excludeDatatypes, Concept answersToConcept, Integer start, Integer size)
	        throws DAOException {
		if (StringUtils.isBlank(phrase)) {
			phrase = "%"; // match all
		}
		
		Criteria searchCriteria = createConceptWordSearchCriteria(phrase, locales, includeRetired, requireClasses,
		    excludeClasses, requireDatatypes, excludeDatatypes, answersToConcept);
		
		List<ConceptSearchResult> results = new Vector<ConceptSearchResult>();
		
		if (searchCriteria != null) {
			ProjectionList pl = Projections.projectionList();
			pl.add(Projections.property("cw1.concept"));
			//this will result in duplicate concepts if two concept names for the same concept match the phrase
			pl.add(Projections.groupProperty("cw1.conceptName"));
			pl.add(Projections.property("cw1.word"));
			//if we have multiple words for the same concept, get the one with a highest weight
			pl.add(Projections.max("cw1.weight"), "maxWeight");
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
				Concept c = (Concept) list.get(0);
				ConceptSearchResult csr = (new ConceptSearchResult((String) list.get(2), c, (ConceptName) list.get(1),
				        (Double) list.get(3)));
				if (!results.contains(csr)) {
					results.add(csr);
				}
			}
		}
		
		return results;
	}