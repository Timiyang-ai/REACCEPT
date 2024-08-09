@Override
	public Genotype<G> newInstance() {
		return new Genotype<>(_chromosomes.map(c -> c.newInstance()), _ngenes);
	}