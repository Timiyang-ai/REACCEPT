	@Test
	public void ofPermutation() {
		final Codec<ISeq<String>, EnumGene<String>> codec = Codecs
			.ofPermutation(ISeq.of("foo", "bar", "zoo"));

		final Genotype<EnumGene<String>> gt = codec.encoding().newInstance();
		assertEquals(gt.length(), 1);

		final Function<Genotype<EnumGene<String>>, ISeq<String>> f = codec.decoder();
		final ISeq<String> value = f.apply(gt);
		assertEquals(value.length(), gt.getChromosome().length());

		for (int i = 0; i < value.length(); ++i) {
			assertEquals(value.get(i), gt.get(0, i).toString());
		}
	}