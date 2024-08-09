@Test(expected = APIException.class)
@Verifies(value = "should fail if the visit has encounters associated to it, including voided", method = "purgeVisit(Visit)")
public void purgeVisit_shouldFailIfTheVisitHasEncountersAssociatedToIt_includingVoided() throws Exception {
    Visit visit = Context.getVisitService().getVisit(1);
    Encounter e = Context.getEncounterService().getEncounter(3);
    e.setVisit(visit);
    Context.getEncounterService().saveEncounter(e);
    // Set encounter to voided
    e.setVoided(true);
    Context.getEncounterService().saveEncounter(e);
    // Sanity check to ensure that voided encounters are also considered
    Assert.assertTrue(Context.getEncounterService().getEncountersByVisit(visit, true).size() > 0);
    Context.getVisitService().purgeVisit(visit);
}