@Test
	public void fit() {
		int x0 = 10,y0 = 15;
		int x1 = 60,y1 = 99;

		List<Point2D_I32> points = new ArrayList<Point2D_I32>();
		addPoints(x0,y0,x1,y0,points);
		addPoints(x1,y0,x1,y1,points);
		addPoints(x1,y1,x0,y1,points);
		addPoints(x0, y1, x0, y0, points);

		FitQuadrilaterialEM alg = new FitQuadrilaterialEM();

		GrowQueue_I32 corners = new GrowQueue_I32();
		corners.add(0);
		corners.add(10); // extra corner
		corners.add(50);
		corners.add(50+84);
		corners.add(50+84+50);

		Quadrilateral_F64 result = new Quadrilateral_F64();
		alg.fit(points,corners,result);

		List<Point2D_F64> expected = new ArrayList<Point2D_F64>();
		expected.add( new Point2D_F64(x0,y0));
		expected.add( new Point2D_F64(x1,y0));
		expected.add( new Point2D_F64(x1,y1));
		expected.add( new Point2D_F64(x0,y1));

		assertTrue(findMatch(result.a,expected));
		assertTrue(findMatch(result.b,expected));
		assertTrue(findMatch(result.c,expected));
		assertTrue(findMatch(result.d,expected));
	}