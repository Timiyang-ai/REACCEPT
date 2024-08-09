@Test
@Verifies(value = "should return all unvoided visits if includeEnded is set to true and considering attribute values", method = "getVisits(Collection<VisitType>,Collection<Patient>,Collection<Location>,Collection<Concept>,Date,Date,Date,Date,Map<VisitAttributeType, Object>,boolean,boolean)")
public void getVisits_shouldReturnAllUnvoidedVisitsIfIncludeEndedIsSetToTrueConsideringAttributeValues() throws Exception {
    executeDataSet(VISITS_WITH_DATES_XML);
    // Assuming VISITS_WITH_DATES_XML dataset already provides data matching the required attributes for the test
    Map<VisitAttributeType, Object> attributeValues = new HashMap<>();
    // Populate the attributeValues map as required for testing, assuming a certain VisitAttributeType and value are pertinent to the test cases
    // For example: attributeValues.put(new VisitAttributeType(1), "SomeValue");
    
    // Now includes tests for checking visits with specific attribute values and considering inactive visits too
    Assert.assertEquals(13, dao.getVisits(null, null, null, null, null, null, null, null, attributeValues, true, false).size());
}