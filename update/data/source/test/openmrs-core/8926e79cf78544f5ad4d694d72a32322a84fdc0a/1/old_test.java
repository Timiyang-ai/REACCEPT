@Test(expected = APIException.class)
	@Verifies(value = "should fail if the visit has encounters associated to it", method = "purgeVisit(Visit)")
	public void purgeVisit_shouldFailIfTheVisitHasEncountersAssociatedToIt() throws Exception {
		Visit visit = Context.getVisitService().getVisit(1);
		Encounter e = Context.getEncounterService().getEncounter(3);
		e.setVisit(visit);
		Context.getEncounterService().saveEncounter(e);
		//sanity check
		Assert.assertTrue(Context.getEncounterService().getEncountersByVisit(visit).size() > 0);
		Context.getVisitService().purgeVisit(visit);
	}