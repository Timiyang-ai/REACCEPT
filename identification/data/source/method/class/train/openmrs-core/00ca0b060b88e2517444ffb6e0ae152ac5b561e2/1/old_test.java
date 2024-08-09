@Test
	public void getDrugDurationUnits_shouldReturnAListIfGPIsSet() throws Exception {
		
		assertThat(orderService.getDrugDurationUnits(), containsInAnyOrder(hasId(28)));
	}