@Test
	@Verifies(value = "should include retired types in the results", method = "findEncounterTypes(String)")
	public void findEncounterTypes_shouldIncludeRetiredTypesInTheResults() throws Exception {
		EncounterService encounterService = Context.getEncounterService();
		List<EncounterType> types = encounterService.findEncounterTypes("Test Enc");
		assertEquals(3, types.size());
		
		// make sure at least one of the types was retired
		boolean foundRetired = false;
		for (EncounterType type : types) {
			if (type.isRetired())
				foundRetired = true;
		}
		assertTrue("Retired types should be returned as well", foundRetired);
	}