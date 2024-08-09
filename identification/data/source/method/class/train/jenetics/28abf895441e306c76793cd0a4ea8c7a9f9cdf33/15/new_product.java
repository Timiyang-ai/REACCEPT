public static <G extends Gene<?, G>, C extends Comparable<? super C>>
	Builder<G, C> builder(
		final Function<? super Genotype<G>, ? extends C> ff,
		final Factory<Genotype<G>> gtf
	) {
		return new Builder<>(Evaluators.concurrent(ff, commonPool()), gtf);
	}