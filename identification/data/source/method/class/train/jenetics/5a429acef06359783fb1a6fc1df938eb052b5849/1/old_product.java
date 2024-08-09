@Override
	public Genotype<T> newInstance() {
		final Genotype<T> genotype = new Genotype<T>(_chromosomes.length());
		final Factory<Chromosome<T>> factory = _chromosomes.get(0); 
		genotype._chromosomes.fill(factory);
		return genotype;
	}