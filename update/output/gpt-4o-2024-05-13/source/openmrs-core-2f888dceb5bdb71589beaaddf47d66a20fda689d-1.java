@Test
@Verifies(value = "should not return concepts with matching names that are voided", method = "getConcepts(String,Locale,null,List<QConceptClass;>,List<QConceptDatatype;>)")
public void getConcepts_shouldNotReturnConceptsWithMatchingNamesThatAreVoided() throws Exception {
    Concept concept = dao.getConcept(7);
    Context.getConceptService().updateConceptIndex(concept);
    
    // Mock the AdministrationService to return the desired case sensitivity setting
    AdministrationService adminService = Mockito.mock(AdministrationService.class);
    Mockito.when(adminService.isDatabaseStringComparisonCaseSensitive()).thenReturn(true);
    Context.setAdministrationService(adminService);
    
    Assert.assertEquals(0, dao.getConcepts("VOIDED", null, false, new ArrayList<ConceptClass>(),
        new ArrayList<ConceptDatatype>()).size());
}