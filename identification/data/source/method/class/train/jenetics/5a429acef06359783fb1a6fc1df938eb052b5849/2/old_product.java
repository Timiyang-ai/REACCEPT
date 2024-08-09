@Override
	public Genotype<T> newInstance() {
		final Array<Chromosome<T>> chromosomes = new Array<>(length());
		for (int i = 0; i < length(); ++i) {
			chromosomes.set(i, _chromosomes.get(i).newInstance());
		}

		return new Genotype<>(chromosomes.toISeq(), _ngenes);
	}