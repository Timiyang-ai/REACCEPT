	@Test
	public void beforeCreateEncounter_shouldAssignExistingVisitIfMatchFound() {
		Encounter encounter = Context.getEncounterService().getEncounter(1);
		Assert.assertNull(encounter.getVisit());
		
		new ExistingVisitAssignmentHandler().beforeCreateEncounter(encounter);
		
		Assert.assertNotNull(encounter.getVisit());
		Assert.assertNotNull(encounter.getVisit().getVisitId());
	}