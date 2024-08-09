	@Test
	public void getAllVisits_shouldReturnAllUnvoidedVisits() {
		assertEquals(5, visitService.getAllVisits().size());
	}