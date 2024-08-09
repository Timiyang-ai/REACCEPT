	@Test
	public void findEncounterTypes_shouldReturnTypesByPartialNameMatch() {
		EncounterService encounterService = Context.getEncounterService();
		List<EncounterType> types = encounterService.findEncounterTypes("Test Enc");
		assertEquals(3, types.size());
	}