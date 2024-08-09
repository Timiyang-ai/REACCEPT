@SafeVarargs
	public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	Builder<G, C> newBuilder(
		final Function<? super Genotype<G>, ? extends C> fitnessFunction,
		final Chromosome<G>... chromosomes
	) {
		return new Builder<>(Genotype.of(chromosomes), fitnessFunction);
	}