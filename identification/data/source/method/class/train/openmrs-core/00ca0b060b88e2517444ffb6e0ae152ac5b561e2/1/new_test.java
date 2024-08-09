@Test
	public void getDurationUnits_shouldReturnAListIfGPIsSet() throws Exception {
		assertThat(orderService.getDurationUnits(), containsInAnyOrder(hasId(28)));
	}