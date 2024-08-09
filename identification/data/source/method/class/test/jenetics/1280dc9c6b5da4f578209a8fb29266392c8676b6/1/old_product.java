public static <G> EnumGene<G> valueOf(final G[] validAlleles) {
		return valueOf(Array.of(validAlleles).toISeq());
	}