	@Test
	public void getTestSpecimenSources_shouldReturnAListIfGPIsSet() {
		List<Concept> specimenSourceList = orderService.getTestSpecimenSources();
		assertEquals(1, specimenSourceList.size());
		assertEquals(22, specimenSourceList.get(0).getConceptId().intValue());
	}