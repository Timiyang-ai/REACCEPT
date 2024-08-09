@Override
	public Genotype<G> newInstance() {
		return new Genotype<G>(_chromosomes.map(c -> c.newInstance()), _ngenes);
	}