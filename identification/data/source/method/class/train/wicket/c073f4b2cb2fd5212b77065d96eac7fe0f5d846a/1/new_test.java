@Test
	public void testResolveRelative_DotFollowedByEmptySegment2()
	{
		Url relative = Url.parse("./?a=b");
		Url baseUrl = Url.parse("bar/baz");
		baseUrl.resolveRelative(relative);

		assertEquals("bar/?a=b", baseUrl.toString());
	}