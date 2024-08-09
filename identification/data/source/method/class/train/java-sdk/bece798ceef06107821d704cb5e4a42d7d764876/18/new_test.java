@Test
  public void testGetDocument() {
    final Document document = service.getDocument(EXAMPLE_DOCUMENT);
    Assert.assertNotNull(document);
  }