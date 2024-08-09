@Test
	@Verifies(value = "should not get by inexact name", method = "getEncounterType(String)")
	public void getEncounterType_shouldNotGetByInexactName() throws Exception {
		EncounterService encounterService = Context.getEncounterService();
		
		// we should not get two types here, the second one is retired
		EncounterType type = encounterService.getEncounterType("Test Enc Type A");
		assertEquals(2, type.getEncounterTypeId().intValue());
		
		// we should not get any encounters here even though "Test Enc" is similar
		// to a name that is in the db
		EncounterType typeByInExactName = encounterService.getEncounterType("Test Enc");
		assertNull(typeByInExactName);
	}