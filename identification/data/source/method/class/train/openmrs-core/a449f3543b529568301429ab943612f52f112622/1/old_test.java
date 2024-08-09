	@Test
	public void registerHandler_shouldRegisterHandlerWithTheGivenKey() {
		ObsService os = Context.getObsService();
		
		os.registerHandler("DummyHandler", new ImageHandler());
		
		ComplexObsHandler dummyHandler = os.getHandler("DummyHandler");
		Assert.assertNotNull(dummyHandler);
	}