@Test
	@Verifies(value = "should include retired providers if includeRetired is set to true", method = "getCountOfProviders(String,null)")
	public void getCountOfProviders_shouldIncludeRetiredProvidersIfIncludeRetiredIsSetToTrue() throws Exception {
		assertEquals(4, service.getCountOfProviders("provider", true).intValue());
	}