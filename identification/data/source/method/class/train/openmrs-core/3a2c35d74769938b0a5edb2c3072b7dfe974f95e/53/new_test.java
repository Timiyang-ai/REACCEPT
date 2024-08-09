	@Test
	public void getEncountersByPatientIdentifier_shouldNotGetVoidedEncounters() {
		EncounterService encounterService = Context.getEncounterService();
		
		List<Encounter> encounters = encounterService.getEncountersByPatientIdentifier("12345");
		assertEquals(2, encounters.size());
	}