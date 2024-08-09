@Test
@Verifies(value = "should return all unvoided visits if includeInactive is set to true", method = "getVisits(Collection<VisitType>,Collection<Patient>,Collection<Location>,Collection<Concept>,Date,Date,Date,Date,Map<VisitAttributeType, Object>,boolean,boolean)")
public void getVisits_shouldReturnAllUnvoidedVisitsIfIncludeInactiveIsSetToTrue() throws Exception {
    executeDataSet(VISITS_WITH_DATES_XML);
    
    // Assuming the method to create or obtain VisitAttributeType instances and corresponding attribute values
    Map<VisitAttributeType, Object> attributeValues = new HashMap<>();
    // Example: attributeValues.put(new VisitAttributeType(1), "SomeValue"); // Placeholder for actual attribute setup
    
    Assert.assertEquals(13, Context.getVisitService().getVisits(null, null, null, null, null, null, null, null, attributeValues, true, false).size());
}