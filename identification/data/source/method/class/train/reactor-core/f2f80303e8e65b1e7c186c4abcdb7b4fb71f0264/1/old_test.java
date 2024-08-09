	@Test
	public void mergeAdditionalSource() {
		Comparator<Integer> originalComparator = Comparator.naturalOrder();
		@SuppressWarnings("unchecked")
		FluxMergeOrdered<Integer> fmo = new FluxMergeOrdered<>(2,
				Queues.small(),
				originalComparator,
				Flux.just(1, 2),
				Flux.just(3, 4));

		FluxMergeOrdered<Integer> fmo2 = fmo.mergeAdditionalSource(Flux.just(5, 6), Comparator.naturalOrder());

		assertThat(fmo2).isNotSameAs(fmo);
		assertThat(fmo2.sources).startsWith(fmo.sources)
		                        .hasSize(3);
		assertThat(fmo.sources).hasSize(2);
		assertThat(fmo2.valueComparator)
				.as("same comparator detected and used")
				.isSameAs(originalComparator);

		StepVerifier.create(fmo2)
		            .expectNext(1, 2, 3, 4, 5, 6)
		            .verifyComplete();
	}