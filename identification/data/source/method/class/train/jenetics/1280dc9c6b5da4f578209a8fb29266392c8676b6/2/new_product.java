@Deprecated
	public static <G> EnumGene<G> valueOf(
		final G[] validAlleles,
		final int alleleIndex
	) {
		return of(validAlleles, alleleIndex);
	}