@Test(expected = APIException.class)
@Verifies(value = "should fail if the visit has encounters associated to it, including voided ones", method = "purgeVisit(Visit)")
public void purgeVisit_shouldFailIfTheVisitHasEncountersAssociatedToItIncludingVoided() throws Exception {
    Visit visit = Context.getVisitService().getVisit(1);
    Encounter e = Context.getEncounterService().getEncounter(3);
    e.setVisit(visit);
    // Assuming there's a method to void an encounter for the sake of this example
    e.setVoided(true);
    Context.getEncounterService().saveEncounter(e);
    // Sanity check to ensure encounters, including voided ones, are associated with the visit
    Assert.assertTrue(Context.getEncounterService().getEncountersByVisit(visit, true).size() > 0);
    Context.getVisitService().purgeVisit(visit);
}