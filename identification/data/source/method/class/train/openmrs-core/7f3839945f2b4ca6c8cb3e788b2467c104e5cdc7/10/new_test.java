	@Test
	public void getEncountersByPatientId_shouldNotGetVoidedEncounters() {
		EncounterService encounterService = Context.getEncounterService();
		
		List<Encounter> encounters = encounterService.getEncountersByPatientId(3);
		assertEquals(2, encounters.size());
	}