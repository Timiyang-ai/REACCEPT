	@Test
	public void empty() {
		Assert.assertNotNull(ISeq.EMPTY);
		Assert.assertNotNull(ISeq.empty());
		Assert.assertSame(ISeq.EMPTY, ISeq.empty());
		Assert.assertEquals(ISeq.EMPTY.length(), 0);
		Assert.assertEquals(ISeq.empty().asList().size(), 0);
	}