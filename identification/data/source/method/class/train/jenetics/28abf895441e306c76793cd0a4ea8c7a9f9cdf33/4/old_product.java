public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	EngineBuilder<G, C> newBuilder(
		final Factory<Genotype<G>> genotypeFactory,
		final Function<? super Genotype<G>, ? extends C> fitnessFunction
	) {
		return new EngineBuilder<>(genotypeFactory, fitnessFunction);
	}