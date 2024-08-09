	@Test
	public void nullSafeEqualsIgnoreCase_shouldBeCaseInsensitive() {
		Assert.assertTrue(OpenmrsUtil.nullSafeEqualsIgnoreCase("equal", "Equal"));
	}