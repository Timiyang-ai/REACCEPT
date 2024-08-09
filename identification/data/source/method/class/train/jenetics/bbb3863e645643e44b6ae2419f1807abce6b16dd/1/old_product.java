public BitChromosome invert() {
		final BitChromosome copy = copy();
		BitUtils.invert(copy._genes);
		return copy;
	}