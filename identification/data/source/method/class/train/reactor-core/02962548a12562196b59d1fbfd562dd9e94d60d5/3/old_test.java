	@Test
	public void from() {
		AssertSubscriber<Integer> ts = AssertSubscriber.create();

		ParallelFlux.from(Flux.range(1, 5), Flux.range(6, 5))
		            .sequential()
		            .subscribe(ts);

		ts.assertContainValues(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
		  .assertNoError()
		  .assertComplete();
	}