	@Test
	public void getEncountersByPatient_shouldNotGetVoidedEncounters() {
		EncounterService encounterService = Context.getEncounterService();
		
		List<Encounter> encounters = encounterService.getEncountersByPatient(new Patient(3));
		assertEquals(2, encounters.size());
	}