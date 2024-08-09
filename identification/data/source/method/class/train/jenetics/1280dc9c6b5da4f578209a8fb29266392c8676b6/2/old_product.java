public static <G> EnumGene<G> valueOf(
		final G[] validAlleles,
		final int alleleIndex
	) {
		return valueOf(Array.of(validAlleles).toISeq(), alleleIndex);
	}