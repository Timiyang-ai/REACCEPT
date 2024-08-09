public static <G extends Gene<?, G>, C extends Comparable<? super C>>
		Evaluator<G, C> of(final GenotypeEvaluator<G, C> evaluator) {
			requireNonNull(evaluator);

			return population -> {
				final ISeq<Genotype<G>> genotypes = population.stream()
					.filter(pt -> !pt.isEvaluated())
					.map(Phenotype::getGenotype)
					.collect(ISeq.toISeq());

				if (genotypes.nonEmpty()) {
					final ISeq<C> results = evaluator.evaluate(
						genotypes,
						population.get(0).getFitnessFunction()
					);

					if (genotypes.size() != results.size()) {
						throw new IllegalStateException(format(
							"Expected %d results, but got %d. " +
							"Check your evaluator function.",
							genotypes.size(), results.size()
						));
					}

					final MSeq<Phenotype<G, C>> evaluated = population.asMSeq();
					for (int i = 0, j = 0; i < evaluated.length(); ++i) {
						if (!population.get(i).isEvaluated()) {
							evaluated.set(
								i,
								population.get(i).withFitness(results.get(j++))
							);
						}
					}

					return evaluated.toISeq();
				} else {
					return population.asISeq();
				}
			};
		}