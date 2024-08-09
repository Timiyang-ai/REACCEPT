	@Test
	public void addObs_shouldAddObsWithNullValues() {
		Encounter encounter = new Encounter();
		encounter.addObs(new Obs());
		assertEquals(1, encounter.getAllObs(true).size());
	}