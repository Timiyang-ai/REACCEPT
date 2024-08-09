	@Test
	public void getHandler_shouldHaveDefaultImageAndTextHandlersRegisteredBySpring() {
		ObsService os = Context.getObsService();
		ComplexObsHandler imgHandler = os.getHandler("ImageHandler");
		Assert.assertNotNull(imgHandler);
		
		ComplexObsHandler textHandler = os.getHandler("TextHandler");
		Assert.assertNotNull(textHandler);
	}