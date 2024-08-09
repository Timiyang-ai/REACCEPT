	@Test(dataProvider = "crossoverParameters")
	public void crossover(
		final String stringA,
		final String stringB,
		final Seq<Integer> points,
		final String expectedA,
		final String expectedB
	) {
		final ISeq<Character> a = CharSeq.toISeq(stringA);
		final ISeq<Character> b = CharSeq.toISeq(stringB);

		final MSeq<Character> ma = a.copy();
		final MSeq<Character> mb = b.copy();

		final int[] intPoints = points.stream()
			.mapToInt(Integer::intValue)
			.toArray();

		MultiPointCrossover.crossover(ma, mb, intPoints);
		Assert.assertEquals(toString(ma), expectedA);
		Assert.assertEquals(toString(mb), expectedB);
	}