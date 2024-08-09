@Test(expected = APIException.class)
public void purgeVisit_shouldFailIfTheVisitHasEncountersAssociatedToIt() throws Exception {
    Visit visit = Context.getVisitService().getVisit(1);
    Encounter e = Context.getEncounterService().getEncounter(3);
    e.setVisit(visit);
    Context.getEncounterService().saveEncounter(e);
    
    // Sanity check to ensure there are encounters associated with the visit, including voided ones
    Assert.assertTrue(Context.getEncounterService().getEncountersByVisit(visit, true).size() > 0);
    
    // Attempt to purge the visit should fail due to associated encounters
    Context.getVisitService().purgeVisit(visit);
}