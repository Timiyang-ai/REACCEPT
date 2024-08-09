	@Test
	public void getVisitTypeByUuid_shouldGetCorrentVisitType() {
		VisitType visitType = visitService.getVisitTypeByUuid("c0c579b0-8e59-401d-8a4a-976a0b183519");

		assertNotNull(visitType);
		assertEquals("Initial HIV Clinic Visit", visitType.getName());
	}