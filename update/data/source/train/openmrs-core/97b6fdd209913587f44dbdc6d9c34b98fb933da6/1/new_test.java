@SuppressWarnings("unchecked")
	@Test
	@Ignore
	@Verifies(value = "should return correct results for concept with names that contains words with more weight", method = "getConcepts(String,List<Locale>,null,List<ConceptClass>,List<ConceptClass>,List<ConceptDatatype>,List<ConceptDatatype>,Concept,Integer,Integer)")
	public void getConcepts_shouldReturnCorrectResultsForConceptWithNamesThatContainsWordsWithMoreWeight() throws Exception {
		executeDataSet("org/openmrs/api/include/ConceptServiceTest-words.xml");
		Concept conceptWithMultipleMatchingNames = dao.getConcept(3000);
		//recalculate the weights just in case the logic for calculating the weights is changed
		ConceptService cs = Context.getConceptService();
		cs.updateConceptIndex(conceptWithMultipleMatchingNames);
		cs.updateConceptIndex(dao.getConcept(4000));
		List<ConceptSearchResult> searchResults = dao
		        .getConcepts("trust", Collections.singletonList(Locale.ENGLISH), false, Collections.EMPTY_LIST,
		            Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST, null, null, null);
		
		Assert.assertEquals(2, searchResults.size());
		//the first concept is the one with a word with the highest weight
		Assert.assertEquals(conceptWithMultipleMatchingNames, searchResults.get(0).getConcept());
		//For conceptId=3000, its search result should ALWAYS match on 'TRUST ME' because it is shorter THAN 'TRUST ALWAYS'
		Assert.assertEquals(9998, searchResults.get(0).getConceptName().getConceptNameId().intValue());
	}