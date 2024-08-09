	@Test
	public void sanitizeLocales_shouldSkipOverInvalidLocales() {
		Assert.assertEquals("fr_RW, it, en", WebUtil.sanitizeLocales("és, qqqq, fr_RW, it, enñ"));
	}