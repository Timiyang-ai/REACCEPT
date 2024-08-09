@Override
	public Genotype<T> newInstance() {
		final Array<Chromosome<T>> chromosomes = new Array<Chromosome<T>>(length());
		for (int i = 0; i < length(); ++i) {
			chromosomes.set(i, _chromosomes.get(i).newInstance());
		}
		
		return new Genotype<T>(chromosomes.toISeq(), _ngenes);
	}