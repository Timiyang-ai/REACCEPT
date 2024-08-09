	@Test
	public void empty() {
		Assert.assertNotNull(MSeq.EMPTY);
		Assert.assertNotNull(MSeq.empty());
		Assert.assertSame(MSeq.EMPTY, MSeq.empty());
		Assert.assertEquals(MSeq.EMPTY.length(), 0);
		Assert.assertEquals(MSeq.empty().asList().size(), 0);
	}