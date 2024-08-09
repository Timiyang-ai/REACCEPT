	@Test
	public void purgeEncounterType_shouldPurgeType() {
		EncounterService encounterService = Context.getEncounterService();
		
		EncounterType encounterTypeToPurge = encounterService.getEncounterType(4);
		assertNotNull(encounterTypeToPurge);
		
		// check deletion
		encounterService.purgeEncounterType(encounterTypeToPurge);
		assertNull(encounterService.getEncounterType(4));
	}