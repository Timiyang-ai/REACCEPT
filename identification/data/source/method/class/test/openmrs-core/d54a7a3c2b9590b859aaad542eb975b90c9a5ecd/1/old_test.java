	@Test
	public void getVisitByUuid_shouldReturnAVisitMatchingTheSpecifiedUuid() {
		Visit visit = visitService.getVisitByUuid("1e5d5d48-6b78-11e0-93c3-18a905e044dc");
		assertNotNull(visit);
		assertEquals(1, visit.getId().intValue());
	}