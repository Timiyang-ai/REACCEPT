@Test
	@Verifies(value = "should not return concepts with matching names that are voided", method = "getConcepts(String,Locale,null,List<QConceptClass;>,List<QConceptDatatype;>)")
	public void getConcepts_shouldNotReturnConceptsWithMatchingNamesThatAreVoided() throws Exception {
		Concept concept = dao.getConcept(7);
		Context.getConceptService().updateConceptIndex(concept);
		Assert.assertEquals(0, dao.getConcepts("VOIDED", null, false, new ArrayList<ConceptClass>(),
		    new ArrayList<ConceptDatatype>()).size());
	}