public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	EngineBuilder<G, C> newBuilder(
		final Function<? super Genotype<G>, ? extends C> fitnessFunction,
		final Factory<Genotype<G>> genotypeFactory
	) {
		return new EngineBuilder<>(genotypeFactory, fitnessFunction);
	}