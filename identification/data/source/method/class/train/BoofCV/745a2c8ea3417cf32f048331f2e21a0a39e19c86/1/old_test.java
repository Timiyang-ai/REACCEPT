@Test
	public void fit_obvious() {
		List<Point2D_I32> contours = createSquare(10,12,20,30);
		GrowQueue_I32 corners = createSquareCorners(10, 12, 20, 30);
		corners.add(corners.get(3)+4);

		MinimizeEnergyPrune alg = new MinimizeEnergyPrune();

		GrowQueue_I32 output = new GrowQueue_I32();
		alg.fit(contours,corners,output);

		assertEquals(4, output.size());
		for (int i = 0; i < 4; i++) {
			assertEquals(corners.get(i),output.get(i));
		}
	}