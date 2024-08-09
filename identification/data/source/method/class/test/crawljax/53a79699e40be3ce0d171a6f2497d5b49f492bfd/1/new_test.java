@Test
	public void testGetXpathExpression() throws IOException {
		String html =
				"<body><div id='firstdiv'></div><div><span id='thespan'>"
						+ "<a id='thea'>test</a></span></div></body>";

		Document dom = DomUtils.asDocument(html);
		assertNotNull(dom);

		// first div
		String expectedXpath = "//DIV[@id = 'firstdiv']";
		String xpathExpr = XPathHelper.getXPathExpression(dom.getElementById("firstdiv"));
		assertEquals(expectedXpath, xpathExpr);

		// span
		expectedXpath = "//SPAN[@id = 'thespan']";
		xpathExpr = XPathHelper.getXPathExpression(dom.getElementById("thespan"));
		assertEquals(expectedXpath, xpathExpr);

		// a
		expectedXpath = "//A[@id = 'thea']";
		xpathExpr = XPathHelper.getXPathExpression(dom.getElementById("thea"));
		assertEquals(expectedXpath, xpathExpr);

		//test anchoring to parent id
		html = "<body><div id='firstdiv'><span><div></div></span></div>"
				+ "<div><span id='thespan'><div></div></span><span></span></div></body>";

		dom = DomUtils.asDocument(html);

		expectedXpath = "//DIV[@id = 'firstdiv']/SPAN[1]/DIV[1]";
		xpathExpr = XPathHelper.getXPathExpression(
				dom.getElementById("firstdiv").getFirstChild().getFirstChild());
		assertEquals(expectedXpath, xpathExpr);

		expectedXpath = "//SPAN[@id = 'thespan']/DIV[1]";
		xpathExpr = XPathHelper.getXPathExpression(
				dom.getElementById("thespan").getFirstChild());
		assertEquals(expectedXpath, xpathExpr);

		//un-anchored: xpath should go to root
		expectedXpath = "/HTML[1]/BODY[1]/DIV[2]/SPAN[2]";
		xpathExpr = XPathHelper.getXPathExpression(
				dom.getFirstChild().getLastChild().getLastChild().getLastChild());
		assertEquals(expectedXpath, xpathExpr);
	}