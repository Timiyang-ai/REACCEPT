public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	Builder<G, C> builder(
		final Factory<Genotype<G>> genotypeFactory,
		final Evaluator<G, C> evaluator
	) {
		return new Builder<>(genotypeFactory, evaluator);
	}