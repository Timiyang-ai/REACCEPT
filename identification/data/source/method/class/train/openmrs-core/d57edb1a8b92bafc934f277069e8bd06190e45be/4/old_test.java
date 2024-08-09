	@Test
	public void beforeCreateEncounter_shouldAssignExistingVisitIfMatchFound() {
		Encounter encounter = Context.getEncounterService().getEncounter(1);
		Assert.assertNull(encounter.getVisit());
		
		new ExistingOrNewVisitAssignmentHandler().beforeCreateEncounter(encounter);
		
		Assert.assertNotNull(encounter.getVisit());
	}