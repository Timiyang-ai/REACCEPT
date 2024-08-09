	@Test
	public void saveEncounterType_shouldSaveEncounterType() {
		EncounterService encounterService = Context.getEncounterService();
		
		EncounterType encounterType = new EncounterType("testing", "desc");
		encounterService.saveEncounterType(encounterType);
		
		// make sure an encounter type id was created
		assertNotNull(encounterType.getEncounterTypeId());
		
		// make sure we can fetch this new encounter type
		// from the database
		EncounterType newEncounterType = encounterService.getEncounterType(encounterType.getEncounterTypeId());
		assertNotNull(newEncounterType);
	}