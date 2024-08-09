	@Test
	public void getAllVisitTypes_shouldGetAllVisitTypes() {
		List<VisitType> visitTypes = visitService.getAllVisitTypes();
		assertEquals(3, visitTypes.size());
	}