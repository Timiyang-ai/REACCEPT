	@Test
	public void stripXPathToElement() {
		String xPath = "/HTML/BODY/DIV/UL/LI[@class=\"Test\"]";
		assertEquals("/HTML/BODY/DIV/UL/LI", XPathHelper.stripXPathToElement(xPath));
	}