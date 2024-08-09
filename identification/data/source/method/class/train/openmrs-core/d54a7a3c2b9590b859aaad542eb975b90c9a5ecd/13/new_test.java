	@Test
	public void getActiveVisitsByPatient_shouldReturnAllUnvoidedActiveVisitsForTheSpecifiedPatient() {
		executeDataSet(VISITS_WITH_DATES_XML);
		assertEquals(4, visitService.getActiveVisitsByPatient(new Patient(2)).size());
	}