@Deprecated
	public static <G> EnumGene<G> valueOf(final ISeq<G> validAlleles) {
		return new EnumGene<>(validAlleles);
	}