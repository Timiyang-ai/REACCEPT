	@Test
	public void getDrug_shouldReturnTheMatchingDrugObject() {
		String drugName = "ASPIRIN";
		String drugUuid = "05ec820a-d297-44e3-be6e-698531d9dd3f";
		Drug drug = conceptService.getDrugByUuid(drugUuid);
		assertEquals(drug, conceptService.getDrug(drugName));
	}