@Test
	@Verifies(value = "should not return retired types", method = "getAllEncounterTypes(boolean)")
	public void getAllEncounterTypes_shouldNotReturnRetiredTypes() throws Exception {
		EncounterService encounterService = Context.getEncounterService();
		List<EncounterType> encounterTypes = encounterService.getAllEncounterTypes(false);
		
		//make sure we get a list
		assertNotNull(encounterTypes);
		
		// make sure we only get the two non retired encounter types
		// defined in the initialData.xml
		assertEquals(4, encounterTypes.size());
	}