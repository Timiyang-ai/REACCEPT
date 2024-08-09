	@Test
	public void dominance() {
		final double[] d1 = new Random().doubles(10).toArray();
		final double[] d2 = new Random().doubles(10).toArray();
		final Vec<double[]> v1 = Vec.of(d1);
		final Vec<double[]> v2 = Vec.of(d2);

		Assert.assertEquals(v1.dominance(v2), Pareto.dominance(d1, d2));
	}