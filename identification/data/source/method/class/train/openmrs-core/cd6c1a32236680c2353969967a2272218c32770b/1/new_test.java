	@Test
	public void getNonCodedDrugConcept_shouldReturnNullIfNothingIsConfigured() {
		adminService.saveGlobalProperty(new GlobalProperty(OpenmrsConstants.GP_DRUG_ORDER_DRUG_OTHER, ""));
		assertNull(orderService.getNonCodedDrugConcept());
	}