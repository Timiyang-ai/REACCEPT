@Test
	public void fitPolygon_loop() {
		List<Point2D_I32> sequence = createRectangle();

		List<PointIndex_I32> result = ShapeFittingOps.fitPolygon(sequence,true,0.1,0.01,100);

		assertEquals(4,result.size());
		check(0, 0, 0, result.get(0));
		check(0, 9, 9, result.get(1));
		check(5,9,14,result.get(2));
		check(5,0,23,result.get(3));
	}