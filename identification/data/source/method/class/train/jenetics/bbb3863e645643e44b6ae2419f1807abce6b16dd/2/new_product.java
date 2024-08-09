public BitChromosome invert() {
		final byte[] data = _genes.clone();
		bit.invert(data);
		return new BitChromosome(data, _length, 1.0 - _p);
	}