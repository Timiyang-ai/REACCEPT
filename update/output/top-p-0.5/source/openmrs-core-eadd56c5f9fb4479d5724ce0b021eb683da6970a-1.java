@Test
@Verifies(value = "should return all unvoided visits if includeEnded and includeInactive are set to true", method = "getVisits(Collection<VisitType>,Collection<Patient>,Collection<Location>,Collection<Concept>,Date,Date,Date,Date,Map<VisitAttributeType, Object>,boolean,boolean)")
public void getVisits_shouldReturnAllUnvoidedVisitsIfIncludeEndedAndIncludeInactiveIsSetToTrue() throws Exception {
    executeDataSet(VISITS_WITH_DATES_XML);
    Map<VisitAttributeType, Object> attributeValues = new HashMap<>();
    // Populate attributeValues as necessary for the test, or leave empty if not testing this functionality specifically
    
    Assert.assertEquals(13, dao.getVisits(null, null, null, null, null, null, null, null, attributeValues, true, false).size());
}