@Test
	@Verifies(value = "should return types ordered on name and nonretired first", method = "findEncounterTypes(String)")
	public void findEncounterTypes_shouldReturnTypesOrderedOnNameAndNonretiredFirst() throws Exception {
		EncounterService encounterService = Context.getEncounterService();
		List<EncounterType> types = encounterService.findEncounterTypes("Test Enc");
		
		// make sure the order is id 2, 3, 1
		assertEquals(2, types.get(0).getEncounterTypeId().intValue());
		assertEquals(3, types.get(1).getEncounterTypeId().intValue());
		assertEquals(1, types.get(2).getEncounterTypeId().intValue());
		
		// this test expects that id #2 and id #3 have the same name and that
		// id #3 is retired
		assertEquals(types.get(0).getName(), types.get(1).getName());
		assertTrue(types.get(1).isRetired());
	}