	@Test
	public void getElementAttributes() throws SAXException, IOException {
		Document dom;
		dom =
				DomUtils.getDocumentNoBalance("<html><body><div class=\"bla\" "
						+ "id=\"test\">Bla</div></body></html>");
		assertEquals("class=bla id=test",
				DomUtils.getAllElementAttributes(dom.getElementById("test")));
	}