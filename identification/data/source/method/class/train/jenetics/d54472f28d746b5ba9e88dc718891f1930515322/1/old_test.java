	@Test
	public void trim() {
		final Random random = new Random();
		final ParetoFront<Vec<double[]>> set = new ParetoFront<>(Vec::dominance);

		final List<Vec<double[]>> elements = IntStream.range(0, 100_000)
			.mapToObj(i -> circle(random))
			.collect(Collectors.toList());

		set.addAll(elements);

		final Set<Vec<double[]>> front = new HashSet<>(
			Pareto.front(Seq.viewOf(elements)).asList()
		);

		Assert.assertEquals(new HashSet<>(set), front);

		final int trimmedSize = set.size()/2;
		Assert.assertTrue(trimmedSize > 0);

		set.trim(trimmedSize, Vec::compare, Vec::distance, Vec::length);
		Assert.assertEquals(set.size(), trimmedSize);

		/*
		final List<Vec<double[]>> missing = set.stream()
			.filter(v -> !front.contains(v))
			.collect(Collectors.toList());

		System.out.println(missing);
		*/
	}