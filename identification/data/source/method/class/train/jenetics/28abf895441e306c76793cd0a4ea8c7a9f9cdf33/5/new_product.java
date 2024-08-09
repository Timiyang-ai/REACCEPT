@SafeVarargs
	public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	Builder<G, C> creator(
		final FitnessEvaluator<G, C> evaluator,
		final Chromosome<G> chromosome,
		final Chromosome<G>... chromosomes
	) {
		return creator(evaluator, Genotype.of(chromosome, chromosomes));
	}