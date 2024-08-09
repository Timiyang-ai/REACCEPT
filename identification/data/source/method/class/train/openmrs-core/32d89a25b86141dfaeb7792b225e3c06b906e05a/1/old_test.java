	@Test
	public void getAllEncounterRoles_shouldGetAllEncounterRolesBasedOnIncludeRetiredFlag() {
		EncounterService encounterService = Context.getEncounterService();
		List<EncounterRole> encounterRoles = encounterService.getAllEncounterRoles(true);
		assertEquals("get all encounter roles including retired", 3, encounterRoles.size());
		encounterRoles = encounterService.getAllEncounterRoles(false);
		assertEquals("get all encounter roles excluding retired", 2, encounterRoles.size());
	}