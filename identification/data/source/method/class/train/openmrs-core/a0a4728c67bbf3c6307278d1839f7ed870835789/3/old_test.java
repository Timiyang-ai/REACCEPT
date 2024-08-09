	@Test
	public void getAllObs_shouldNotReturnNullWithNullObsSet() {
		Encounter encounter = new Encounter();
		assertNotNull(encounter.getAllObs(true));
		assertEquals(encounter.getAllObs(true).size(), 0);
		assertNotNull(encounter.getAllObs(false));
		assertEquals(encounter.getAllObs(false).size(), 0);
	}