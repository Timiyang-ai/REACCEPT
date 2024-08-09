	@Test
	public void normalizeLocale_shouldIgnoreLeadingSpaces() {
		Assert.assertEquals(Locale.ITALIAN, WebUtil.normalizeLocale(" it"));
	}