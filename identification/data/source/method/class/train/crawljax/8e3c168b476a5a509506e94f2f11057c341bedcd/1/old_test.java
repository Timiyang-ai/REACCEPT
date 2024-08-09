@Test
	public void testGetDocument() {
		String html = "<html><body><p/></body></html>";

		try {
			Document doc = DomUtils.getDocument(html);
			assertNotNull(doc);
		} catch (SAXException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}

	}