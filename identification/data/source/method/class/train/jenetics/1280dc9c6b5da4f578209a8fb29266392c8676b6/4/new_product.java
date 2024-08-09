@Deprecated
	public static <A> EnumGene<A> valueOf(
		final ISeq<? extends A> validAlleles,
		final int alleleIndex
	) {
		return new EnumGene<>(validAlleles, alleleIndex);
	}