	@Test
	public void unvoidEncounter_shouldCascadeUnvoidToObs() {
		EncounterService encounterService = Context.getEncounterService();
		
		// get a voided encounter that has some voided obs
		Encounter encounter = encounterService.getEncounter(2);
		encounterService.unvoidEncounter(encounter);
		
		Obs obs = Context.getObsService().getObs(4);
		assertFalse(obs.getVoided());
		assertNull(obs.getVoidReason());
	}