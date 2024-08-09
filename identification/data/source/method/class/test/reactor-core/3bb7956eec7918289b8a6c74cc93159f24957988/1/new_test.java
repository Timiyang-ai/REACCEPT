	@Test
	public void sorted() {
		AssertSubscriber<Integer> ts = AssertSubscriber.create(0);

		Flux.just(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
		    .parallel()
		    .sorted(Comparator.naturalOrder())
		    .subscribe(ts);

		ts.assertNoValues();

		ts.request(2);

		ts.assertValues(1, 2);

		ts.request(5);

		ts.assertValues(1, 2, 3, 4, 5, 6, 7);

		ts.request(3);

		ts.assertValues(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	}