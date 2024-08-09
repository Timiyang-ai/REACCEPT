@Test
	public void testGetXpathExpression() throws SAXException, IOException {
		final String html =
		        "<body><div id='firstdiv'></div><div><span id='thespan'>"
		                + "<a id='thea'>test</a></span></div></body>";

		Document dom = DomUtils.asDocument(html);
		assertNotNull(dom);

		// first div
		String expectedXpath = "/HTML[1]/BODY[1]/DIV[1]";
		String xpathExpr = XPathHelper.getXPathExpression(dom.getElementById("firstdiv"));
		assertEquals(expectedXpath, xpathExpr);

		// span
		expectedXpath = "/HTML[1]/BODY[1]/DIV[2]/SPAN[1]";
		xpathExpr = XPathHelper.getXPathExpression(dom.getElementById("thespan"));
		assertEquals(expectedXpath, xpathExpr);

		// a
		expectedXpath = "/HTML[1]/BODY[1]/DIV[2]/SPAN[1]/A[1]";
		xpathExpr = XPathHelper.getXPathExpression(dom.getElementById("thea"));
		assertEquals(expectedXpath, xpathExpr);
	}