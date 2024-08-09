	@Test
	public void voidEncounter_shouldVoidEncounterAndSetAttributes() {
		EncounterService encounterService = Context.getEncounterService();
		
		// get a nonvoided encounter
		Encounter encounter = encounterService.getEncounter(1);
		assertFalse(encounter.getVoided());
		assertNull(encounter.getVoidedBy());
		assertNull(encounter.getVoidReason());
		assertNull(encounter.getDateVoided());
		
		Encounter voidedEnc = encounterService.voidEncounter(encounter, "Just Testing");
		
		// make sure its still the same object
		assertEquals(voidedEnc, encounter);
		
		// make sure that all the values were filled in
		assertTrue(voidedEnc.getVoided());
		assertNotNull(voidedEnc.getDateVoided());
		assertEquals(Context.getAuthenticatedUser(), voidedEnc.getVoidedBy());
		assertEquals("Just Testing", voidedEnc.getVoidReason());
	}