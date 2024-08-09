	@Test
	public void valueOf() {
		final int length = 100;
		final ISeq<Integer> alleles = MSeq.<Integer>ofLength(length).fill(Int()).toISeq();

		Assert.assertEquals(alleles.length(), length);
		for (int i = 0; i < alleles.length(); ++i) {
			Assert.assertEquals(alleles.get(i), Integer.valueOf(i));
		}

		for (int i = 0; i < alleles.length(); ++i) {
			Assert.assertEquals(new EnumGene<>(i, alleles).getAllele(), Integer.valueOf(i));
			Assert.assertSame(new EnumGene<>(i, alleles).getValidAlleles(), alleles);
		}
	}