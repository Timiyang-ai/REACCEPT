	@Test
	public void setHandlers_shouldAddNewHandlersWithNewKeys() {
		ObsService os = Context.getObsService();
		
		Map<String, ComplexObsHandler> handlers = new HashMap<>();
		handlers.put("DummyHandler4", new ImageHandler());
		handlers.put("DummyHandler5", new BinaryDataHandler());
		handlers.put("DummyHandler6", new TextHandler());
		
		// set the handlers and make sure they're there
		os.setHandlers(handlers);
		
		ComplexObsHandler dummyHandler4 = os.getHandler("DummyHandler4");
		Assert.assertNotNull(dummyHandler4);
		
		ComplexObsHandler dummyHandler5 = os.getHandler("DummyHandler5");
		Assert.assertNotNull(dummyHandler5);
		
		ComplexObsHandler dummyHandler6 = os.getHandler("DummyHandler6");
		Assert.assertNotNull(dummyHandler6);
	}