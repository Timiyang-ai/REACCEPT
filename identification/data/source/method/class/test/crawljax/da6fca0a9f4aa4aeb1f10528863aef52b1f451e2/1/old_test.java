@Test
	public void testGetDocument() {
		String html = "<html><body><p/></body></html>";

		try {
			Document doc = Helper.getDocument(html);
			assertNotNull(doc);
		} catch (SAXException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}

	}