@Test
	public void getHandler_shouldGetAHandlerForTheStringWithRegexLogicalType() throws Exception {
		// ideally we'd have independent web-layer and api-layer tests
		AttributeHandler<?> handler = service.getHandler("regex-validated-string", null);
		Assert.assertTrue(RegexValidatedStringAttributeHandler.class.isAssignableFrom(handler.getClass()));
	}