@Test
	public void testGetDocument() {
		Document document = service.getDocument(EXAMPLE_DOCUMENT);
		Assert.assertNotNull(document);
	}