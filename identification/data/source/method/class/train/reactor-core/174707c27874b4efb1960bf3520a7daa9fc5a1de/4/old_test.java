	@Test
	public void collectSortedList() {
		AssertSubscriber<List<Integer>> ts = AssertSubscriber.create();

		Flux.just(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
		    .parallel()
		    .collectSortedList(Comparator.naturalOrder())
		    .subscribe(ts);

		ts.assertValues(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
	}