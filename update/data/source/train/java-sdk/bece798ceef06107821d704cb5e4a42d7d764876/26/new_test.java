@Test
  public void testGetDocumentProcessingState() {
    final DocumentProcessingStatus documentProcessingState =
        service.getDocumentProcessingState(EXAMPLE_DOCUMENT);
    Assert.assertNotNull(documentProcessingState);
  }