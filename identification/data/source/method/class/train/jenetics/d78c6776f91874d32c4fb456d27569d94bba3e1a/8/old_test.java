	@Test
	public void isValid() {
		final ISeq<Integer> alleles = IntStream.range(0, 100)
			.boxed()
			.collect(ISeq.toISeq());

		final ISeq<EnumGene<Integer>> genes = IntStream.of(comb.subset(100, 10))
			.mapToObj(i -> EnumGene.of(i, alleles))
			.collect(ISeq.toISeq());

		final PermutationChromosome<Integer> ch = new PermutationChromosome<>(genes);
		Assert.assertTrue(ch.isValid());
		Assert.assertEquals(ch.length(), 10);
	}