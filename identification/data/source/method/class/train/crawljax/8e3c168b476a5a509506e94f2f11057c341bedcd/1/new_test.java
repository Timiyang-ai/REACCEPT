@Test
	public void testGetDocument() throws IOException {
		String html = "<html><body><p/></body></html>";
		Document doc = DomUtils.asDocument(html);
		assertNotNull(doc);
	}