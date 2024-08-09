	@Test
	public void getDrugRoutes_shouldGetDrugRoutesAssociatedConceptPrividedInGlobalProperties() {
		List<Concept> drugRoutesList = orderService.getDrugRoutes();
		assertEquals(1, drugRoutesList.size());
		assertEquals(22, drugRoutesList.get(0).getConceptId().intValue());
	}