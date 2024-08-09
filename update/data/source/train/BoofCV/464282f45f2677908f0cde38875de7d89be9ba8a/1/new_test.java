@Test
	public void fitPolygon_loop() {
		List<Point2D_I32> sequence = createRectangle();

		List<PointIndex_I32> result = ShapeFittingOps.fitPolygon(sequence,true,0.05,1.0,100);

		assertEquals(4, result.size());
		checkPolygon(new int[]{0, 0, 0, 9, 5, 9, 5, 0}, new int[]{0, 9, 14, 23}, result);
	}