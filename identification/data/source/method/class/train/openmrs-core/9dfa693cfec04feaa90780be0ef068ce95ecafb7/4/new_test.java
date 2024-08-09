	@Test
	public void getObs_shouldNotReturnNullWithNullObsSet() {
		Encounter encounter = new Encounter();
		
		assertNotNull(encounter.getObs());
		assertEquals(encounter.getObs().size(), 0);
	}