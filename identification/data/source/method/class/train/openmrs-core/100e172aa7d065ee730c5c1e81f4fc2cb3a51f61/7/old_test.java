	@Test
	public void getHandlers_shouldNeverReturnNull() {
		Assert.assertNotNull(Context.getObsService().getHandlers());
		
		// test our current implementation without it being initialized by spring
		Assert.assertNotNull(new ObsServiceImpl().getHandlers());
	}