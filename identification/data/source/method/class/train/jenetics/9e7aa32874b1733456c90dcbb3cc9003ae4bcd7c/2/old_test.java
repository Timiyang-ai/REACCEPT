	@Test
	public void contains() {
		final CharSeq set = new CharSeq(CharSeq.expand("a-z"));
		Assert.assertTrue(set.contains('t'));
		Assert.assertTrue(set.contains('a'));
		Assert.assertTrue(set.contains('z'));
		Assert.assertFalse(set.contains('T'));
		Assert.assertFalse(set.contains('1'));
		Assert.assertFalse(set.contains('Z'));
	}