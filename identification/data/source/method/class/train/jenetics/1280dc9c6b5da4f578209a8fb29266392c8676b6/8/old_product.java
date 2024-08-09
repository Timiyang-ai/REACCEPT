public static <G> EnumGene<G> valueOf(final ISeq<G> validAlleles) {
		if (validAlleles.length() == 0) {
			throw new IllegalArgumentException(
				"Array of valid alleles must be greater than zero."
			);
		}

		@SuppressWarnings("unchecked")
		final EnumGene<G> gene = FACTORY.object();
		gene._validAlleles = validAlleles;
		gene._alleleIndex = RandomRegistry.getRandom().nextInt(validAlleles.length());
		return gene;
	}