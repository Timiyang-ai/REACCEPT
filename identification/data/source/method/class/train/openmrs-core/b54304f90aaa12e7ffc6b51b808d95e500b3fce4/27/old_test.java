	@Test
	public void getObsAtTopLevel_shouldNotReturnNullWithNullObsSet() {
		Encounter encounter = new Encounter();
		assertNotNull(encounter.getObsAtTopLevel(true));
		assertEquals(encounter.getObsAtTopLevel(true).size(), 0);
		assertNotNull(encounter.getObsAtTopLevel(false));
		assertEquals(encounter.getObsAtTopLevel(false).size(), 0);
	}