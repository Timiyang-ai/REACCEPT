@Override
	public BitChromosome newInstance(final Array<BitGene> genes) {
		Validator.notNull(genes, "Genes");
		
		final BitChromosome chromosome = BitChromosome.newInstance(genes.length(), _p);
		
		int ones = 0;
		for (int i = 0; i < genes.length(); ++i) {
			if (genes.get(i) == BitGene.TRUE) {
				++ones;
			}
			BitUtils.setBit(chromosome._genes, i, genes.get(i) == BitGene.TRUE);
		}
		chromosome._p = Probability.valueOf((double)ones/(double)genes.length());
		return chromosome;
	}