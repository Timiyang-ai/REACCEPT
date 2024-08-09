	@Test
	public void getEncounterRoleByUuid_shouldFindEncounterRoleBasedOnUuid() {
		EncounterService encounterService = Context.getEncounterService();
		EncounterRole encounterRole = encounterService.getEncounterRoleByUuid("430bbb70-6a9c-4e1e-badb-9d1054b1b5e9");
		assertNotNull("valid uuid should be returned", encounterRole);
		encounterRole = encounterService.getEncounterRoleByUuid("invaid uuid");
		assertNull("returns null for invalid uuid", encounterRole);
	}