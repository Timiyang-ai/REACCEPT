@Test
	public void fitPolygon_loop() {
		List<Point2D_I32> sequence = createRectangle();

		List<PointIndex_I32> result = ShapeFittingOps.fitPolygon(sequence,true,0.05,0,100);

		assertEquals(4, result.size());
		checkPolygon(new int[]{5, 0, 5, 9, 0, 9, 0, 0}, new int[]{5, 14, 19, 0}, result);
	}