public BitChromosome invert() {
		final BitChromosome copy = copy();
		bit.invert(copy._genes);
		return copy;
	}