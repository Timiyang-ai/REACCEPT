	@Test
	public void getEncounterType_shouldNotGetRetiredTypes() {
		EncounterService encounterService = Context.getEncounterService();
		
		// loop over all types to make sure
		// that the retired "Test Enc Type C" exists
		boolean foundRetired = false;
		for (EncounterType encType : encounterService.getAllEncounterTypes(true)) {
			if (encType.getName().equals("Test Enc Type C") && encType.getRetired()) {
				foundRetired = true;
			}
		}
		assertTrue(foundRetired);
		
		assertNull(encounterService.getEncounterType("Test Enc Type C"));
	}