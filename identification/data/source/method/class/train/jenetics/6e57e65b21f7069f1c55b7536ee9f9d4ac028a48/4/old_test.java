	@Test
	public void asISeq() {
		final Seq<String> iseq = ISeq.of("1");
		Assert.assertSame(iseq.asISeq(), iseq);

		final Seq<String> mseq = MSeq.of("1");
		Assert.assertNotSame(mseq.asISeq(), mseq);
		Assert.assertEquals(mseq.asISeq(), mseq);
	}