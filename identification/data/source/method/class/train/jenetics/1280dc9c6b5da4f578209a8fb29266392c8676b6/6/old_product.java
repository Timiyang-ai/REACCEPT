public static <A> EnumGene<A> valueOf(
		final ISeq<? extends A> validAlleles,
		final int alleleIndex
	) {
		if (validAlleles.length() == 0) {
			throw new IllegalArgumentException(
				"Array of valid alleles must be greater than zero."
			);
		}

		if (alleleIndex < 0 || alleleIndex >= validAlleles.length()) {
			throw new IndexOutOfBoundsException(format(
				"Allele index is not in range [0, %d).", alleleIndex
			));
		}

		@SuppressWarnings("unchecked")
		final EnumGene<A> gene = FACTORY.object();

		gene._validAlleles = cast.apply(validAlleles);
		gene._alleleIndex = alleleIndex;
		return gene;
	}