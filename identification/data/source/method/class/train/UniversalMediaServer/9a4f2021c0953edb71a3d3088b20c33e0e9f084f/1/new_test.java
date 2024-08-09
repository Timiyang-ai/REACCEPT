	@Test(expected=IllegalArgumentException.class)
	public void stripHTMLTest() {
		assertEquals("stripHTMLBasicBody", stripHTML("<html><sometag></sometag><someothertag/><body>Sometext</body></html>"), "Sometext");
		assertEquals("stripHTMLBodyWithTags", stripHTML("<html><sometag></sometag><someothertag/><body>Sometext <strong>someSTRONGtext</strong></body></html>"), "Sometext someSTRONGtext");
		assertEquals("stripHTMLWithoutBody", stripHTML("<html><header></header>Somecontent</html>"), "");
	}