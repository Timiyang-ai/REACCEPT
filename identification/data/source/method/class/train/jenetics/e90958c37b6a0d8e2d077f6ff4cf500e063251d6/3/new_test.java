	@Test
	public void dominance() {
		final ISeq<Vec<double[]>> outline = circle(1000, new Random(234));

		for (Vec<double[]> p : outline) {
			Assert.assertTrue(p.dominance(p) == 0);
			Assert.assertTrue(p.dominance(Vec.of(-1.0, -1.0)) > 0);
			Assert.assertTrue(p.dominance(Vec.of(1.0, 1.0)) < 0);

			Assert.assertTrue(Vec.of(-1.0, -1.0).dominance(p) < 0);
			Assert.assertTrue(Vec.of(1.0, 1.0).dominance(p) > 0);

			for (Vec<double[]> p2 : outline) {
				if (p.dominance(p2) == 0) {
					Assert.assertTrue(p2.dominance(p) == 0);
				}
				if (p.dominance(p2) < 0) {
					Assert.assertTrue(p2.dominance(p) > 0);
				}
				if (p.dominance(p2) > 0) {
					Assert.assertTrue(p2.dominance(p) < 0);
				}
			}
		}
	}