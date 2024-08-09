	@Test
	public void removeTags()
	{
		assertEquals("Test", Text.removeTags("<col=FFFFFF>Test</col>"));
		assertEquals("Test", Text.removeTags("<img=1><s>Test</s>"));
		assertEquals("Zezima  (level-126)", Text.removeTags("<col=ffffff><img=2>Zezima<col=00ffff>  (level-126)"));
		assertEquals("", Text.removeTags("<colrandomtext test>"));
		assertEquals("Not so much.", Text.removeTags("<col=FFFFFF This is a very special message.</col>Not so much."));
		assertEquals("Use Item -> Man", Text.removeTags("Use Item -> Man"));
		assertEquals("a < b", Text.removeTags("a < b"));
		assertEquals("Remove no tags", Text.removeTags("Remove no tags"));
	}