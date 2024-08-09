@Test
	public void prune_obvious() {
		List<Point2D_I32> contours = createSquare(10,12,20,30);
		GrowQueue_I32 corners = createSquareCorners(10, 12, 20, 30);
		corners.add(corners.get(3)+4);

		MinimizeEnergyPrune alg = new MinimizeEnergyPrune(1);

		GrowQueue_I32 output = new GrowQueue_I32();
		alg.prune(contours, corners, output);

		assertEquals(4, output.size());

		// see if the two sets of corners are equivalent, taking in account the possibility of a rotation
		checkMatched(corners, output);

	}