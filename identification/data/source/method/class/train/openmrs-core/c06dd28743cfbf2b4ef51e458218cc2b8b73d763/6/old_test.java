	@Test
	public void getVisitTypes_shouldGetCorrentVisitTypes() {
		List<VisitType> visitTypes = visitService.getVisitTypes("HIV Clinic");
		assertNotNull(visitTypes);
		assertEquals(1, visitTypes.size());
		assertEquals("Initial HIV Clinic Visit", visitTypes.get(0).getName());

		visitTypes = visitService.getVisitTypes("Clinic Visit");
		assertNotNull(visitTypes);
		assertEquals(2, visitTypes.size());
		assertEquals("Initial HIV Clinic Visit", visitTypes.get(0).getName());
		assertEquals("Return TB Clinic Visit", visitTypes.get(1).getName());
	}