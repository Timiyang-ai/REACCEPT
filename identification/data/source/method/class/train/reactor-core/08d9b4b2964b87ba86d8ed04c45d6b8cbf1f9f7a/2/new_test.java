	@Test
	public void wrap() {
		Flux<Integer> m = Flux.wrap(Flux.just(1));
		StepVerifier.create(m)
		            .expectNext(1)
		            .verifyComplete();

		m = Flux.wrap(Flux.just(1).hide());
		StepVerifier.create(m)
		            .expectNext(1)
		            .verifyComplete();
	}