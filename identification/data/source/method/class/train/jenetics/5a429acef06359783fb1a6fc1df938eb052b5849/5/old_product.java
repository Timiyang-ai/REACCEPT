@Override
	public Genotype<T> newInstance() {
		final Genotype<T> genotype = new Genotype<T>(_chromosomes.length());
		
		for (int i = 0; i < _chromosomes.length(); ++i) {
			final Factory<Chromosome<T>> factory = _chromosomes.get(i); 
			genotype._chromosomes.set(i, factory.newInstance());
		}
		
		return genotype;
	}