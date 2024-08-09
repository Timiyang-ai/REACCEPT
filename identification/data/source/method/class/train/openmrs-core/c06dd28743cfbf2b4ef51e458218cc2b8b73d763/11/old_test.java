	@Test
	public void getVisitType_shouldGetCorrectVisitType() {
		VisitType visitType = visitService.getVisitType(1);

		assertNotNull(visitType);
		assertEquals("Initial HIV Clinic Visit", visitType.getName());
	}