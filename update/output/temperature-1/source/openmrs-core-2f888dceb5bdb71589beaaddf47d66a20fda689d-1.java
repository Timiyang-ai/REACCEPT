@Test
@Verifies(value = "should not return concepts with matching names that are voided", method = "getConcepts(String,Locale,null,List<QConceptClass;>,List<QConceptDatatype;>)")
public void getConcepts_shouldNotReturnConceptsWithMatchingNamesThatAreVoided() throws Exception {
    Concept concept = dao.getConcept(7);
    Context.getConceptService().updateConceptIndex(concept);
    // Reflecting the changes in the production method where the case sensitivity is now determined by isDatabaseStringComparisonCaseSensitive
    // Assuming that the setup is in place to mock Context and AdministrationService to return appropriate values for isDatabaseStringComparisonCaseSensitive
    // The actual assertion remains the same as the logic for filtering voided concepts has not changed in the production code diff provided.
    Assert.assertEquals(0, dao.getConcepts("VOIDED", null, false, new ArrayList<ConceptClass>(),
        new ArrayList<ConceptDatatype>()).size());
}