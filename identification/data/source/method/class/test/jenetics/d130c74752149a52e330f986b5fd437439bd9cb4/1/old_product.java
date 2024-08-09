public static Codec<int[], EnumGene<Integer>> ofPermutation(final int length) {
		require.positive(length);

		return Codec.of(
			Genotype.of(PermutationChromosome.ofInteger(length)),
			gt -> gt.getChromosome().toSeq().stream()
				.mapToInt(EnumGene::getAllele)
				.toArray()
		);
	}