	@Test
	public void getEncounterTypeByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "02c533ab-b74b-4ee4-b6e5-ffb6d09a0ac8";
		EncounterType encounterType = Context.getEncounterService().getEncounterTypeByUuid(uuid);
		Assert.assertEquals(6, (int) encounterType.getEncounterTypeId());
	}