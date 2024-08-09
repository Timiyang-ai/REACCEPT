	@Test
	public void getEncountersByVisit_shouldGetActiveEncountersByVisit() {
		List<Encounter> encounters = Context.getEncounterService().getEncountersByVisit(new Visit(1), false);
		assertEquals(1, encounters.size());
	}