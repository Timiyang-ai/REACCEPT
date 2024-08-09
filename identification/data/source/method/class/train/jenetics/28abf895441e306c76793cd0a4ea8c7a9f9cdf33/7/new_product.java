public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	Builder<G, C> creator(
		final FitnessEvaluator<G, C> evaluator,
		final Factory<Genotype<G>> genotypeFactory
	) {
		return new Builder<>(genotypeFactory, evaluator);
	}