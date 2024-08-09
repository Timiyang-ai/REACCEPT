@Test
public void testGetAllCohorts_shouldReturnAllCohortsAndVoided() throws Exception {
    executeDataSet(COHORT_XML);
    
    //data set should have two cohorts, one of which is voided
    List<Cohort> allCohorts = service.getAllCohorts(true);
    assertNotNull(allCohorts);
    assertEquals(2, allCohorts.size());
    assertTrue(allCohorts.get(0).isVoided());
    assertFalse(allCohorts.get(1).isVoided());
    
    // if called with false parameter, should not return the voided one
    allCohorts = service.getAllCohorts(false);
    assertNotNull(allCohorts);
    // only the non-voided cohort should be returned
    assertEquals(1, allCohorts.size());
    assertFalse(allCohorts.get(0).isVoided());
}