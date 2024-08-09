@Override
	public Genotype<T> newInstance() {
		final Genotype<T> genotype = new Genotype<T>(_chromosomes.length());
		for (int i = 0; i < genotype.length(); ++i) {
			genotype._chromosomes.set(i, _chromosomes.get(i).newInstance());
		}
		return genotype;
	}