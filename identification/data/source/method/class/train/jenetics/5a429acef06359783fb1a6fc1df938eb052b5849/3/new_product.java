@Override
	public Genotype<G> newInstance() {
		return new Genotype<>(_chromosomes.map(Factory::newInstance));
	}