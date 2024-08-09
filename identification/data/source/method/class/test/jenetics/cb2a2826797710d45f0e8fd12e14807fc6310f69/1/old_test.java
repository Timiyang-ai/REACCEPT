	@Test
	public void distance() {
		final double[] d1 = new Random().doubles(10).toArray();
		final double[] d2 = new Random().doubles(10).toArray();
		final Vec<double[]> v1 = Vec.of(d1);
		final Vec<double[]> v2 = Vec.of(d2);


		for (int i = 0; i < d1.length; ++i) {
			Assert.assertEquals(d1[i] - d2[i], v1.distance(v2, i));
		}
	}