	@Test
	public void toArray() {
		final Seq<String> mseq = MSeq.of("1");
		final String[] array = mseq.toArray(new String[0]);

		Assert.assertEquals(array.length, mseq.size());
		Assert.assertEquals(array[0], mseq.get(0));
	}