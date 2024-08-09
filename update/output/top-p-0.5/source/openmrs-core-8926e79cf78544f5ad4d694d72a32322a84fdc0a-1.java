@Test(expected = APIException.class)
public void purgeVisit_shouldFailIfTheVisitHasEncountersAssociatedToIt() throws Exception {
    Visit visit = Context.getVisitService().getVisit(1);
    Encounter e = Context.getEncounterService().getEncounter(3);
    e.setVisit(visit);
    Context.getEncounterService().saveEncounter(e);
    //sanity check to ensure encounters are associated with the visit, including voided ones
    Assert.assertTrue(Context.getEncounterService().getEncountersByVisit(visit, true).size() > 0);
    Context.getVisitService().purgeVisit(visit);
}