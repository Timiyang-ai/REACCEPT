	@Test
	public void toString_shouldNotFailWithEmptyObject() {
		EncounterRole encounterRole = new EncounterRole();
		@SuppressWarnings("unused")
		String toStringOutput = encounterRole.toString();
	}