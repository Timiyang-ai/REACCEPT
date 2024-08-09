@Test
public void purgeVisit_shouldFailIfTheVisitHasEncountersAssociatedToIt_includingVoided() throws Exception {
    Visit visit = Context.getVisitService().getVisit(1);
    Encounter e = Context.getEncounterService().getEncounter(3);
    e.setVisit(visit);
    Context.getEncounterService().saveEncounter(e);
    // Sanity check to ensure encounters are associated with the visit, including voided ones.
    assertTrue("Expected at least one encounter associated with the visit, including voided", 
        Context.getEncounterService().getEncountersByVisit(visit, true).size() > 0);

    try {
        Context.getVisitService().purgeVisit(visit);
        fail("Expected APIException was not thrown when trying to purge a visit with associated encounters");
    } catch (APIException ex) {
        String expectedMessage = "Cannot purge a visit that has encounters associated to it";
        String actualMessage = ex.getMessage();
        assertTrue("Exception message does not match expected", actualMessage.contains(expectedMessage));
    }
}