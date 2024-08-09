@SafeVarargs
	public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	Builder<G, C> builder(
		final FitnessEvaluator<G, C> evaluator,
		final Chromosome<G> chromosome,
		final Chromosome<G>... chromosomes
	) {
		return builder(evaluator, Genotype.of(chromosome, chromosomes));
	}