	@Test
	public void asMSeq() {
		final Seq<String> mseq = MSeq.of("1");
		Assert.assertSame(mseq.asMSeq(), mseq);

		final Seq<String> iseq = ISeq.of("1");
		Assert.assertNotSame(iseq.asMSeq(), iseq);
		Assert.assertEquals(iseq.asMSeq(), iseq);
	}