	@Test(invocationCount = 10)
	public void add() {
		final Random random = new Random();

		final List<Vec<double[]>> elements = new ArrayList<>();
		final ParetoFront<Vec<double[]>> set = new ParetoFront<>(Vec::dominance);

		for (int i = 0; i < 500; ++i) {
			final Vec<double[]> point = circle(random);
			elements.add(point);
			set.add(point);

			Assert.assertEquals(
				new HashSet<>(set),
				new HashSet<>(Pareto.front(ISeq.of(elements)).asList())
			);
		}
	}