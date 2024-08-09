	@Test
	public void purgeEncounter_shouldPurgeEncounter() {
		
		EncounterService es = Context.getEncounterService();
		
		// fetch the encounter to delete from the db
		Encounter encounterToDelete = es.getEncounter(1);
		
		es.purgeEncounter(encounterToDelete);
		
		// try to refetch the encounter. should get a null object
		Encounter e = es.getEncounter(encounterToDelete.getEncounterId());
		assertNull("We shouldn't find the encounter after deletion", e);
	}