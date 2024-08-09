@SafeVarargs
	public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	Builder<G, C> builder(
		final Function<? super Genotype<G>, ? extends C> ff,
		final Chromosome<G> chromosome,
		final Chromosome<G>... chromosomes
	) {
		return new Builder<>(Genotype.of(chromosome, chromosomes), ff);
	}