	@Test
	public void getEncounterByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "6519d653-393b-4118-9c83-a3715b82d4ac";
		Encounter encounter = Context.getEncounterService().getEncounterByUuid(uuid);
		Assert.assertEquals(3, (int) encounter.getEncounterId());
	}