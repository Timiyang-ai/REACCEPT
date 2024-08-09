	@Test
	public void htmlspecialchars() {
		String result = BasicLTIUtil.htmlspecialchars("<hi & \"bye' = ><hi & \"bye' = >");
		assertEquals("&lt;hi &amp; &quot;bye' &#61; &gt;&lt;hi &amp; &quot;bye' &#61; &gt;", result);

		result = BasicLTIUtil.htmlspecialchars("nothing to see here");
		assertEquals("nothing to see here", result);

		result = BasicLTIUtil.htmlspecialchars(null);
		assertNull(result);
	}