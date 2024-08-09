@Test
	public void testGetDocumentProcessingState() {
		DocumentProcessingStatus documentProcessingState = service.getDocumentProcessingState(EXAMPLE_DOCUMENT);
		Assert.assertNotNull(documentProcessingState);
	}