@Test
	@Verifies(value = "should return types by partial case insensitive match", method = "findEncounterTypes(String)")
	public void findEncounterTypes_shouldReturnTypesByPartialCaseInsensitiveMatch() throws Exception {
		EncounterService encounterService = Context.getEncounterService();
		List<EncounterType> types = encounterService.findEncounterTypes("Test ENC");
		assertEquals(3, types.size());
	}