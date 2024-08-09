@Test
	public void fit_quad() {
		int x0 = 10,y0 = 15;
		int x1 = 60,y1 = 99;

		List<Point2D_I32> points = new ArrayList<Point2D_I32>();
		addPoints(x0,y0,x1,y0,points);
		addPoints(x1,y0,x1,y1,points);
		addPoints(x1,y1,x0,y1,points);
		addPoints(x0,y1,x0,y0,points);

		RefinePolyLine alg = new RefinePolyLine(true);

		GrowQueue_I32 corners = new GrowQueue_I32();
		corners.add(0);
		corners.add(50);
		corners.add(50+84);
		corners.add(50+84+50);

		alg.fit(points,corners);

		assertEquals(0,   corners.get(0));
		assertEquals(50,  corners.get(1));
		assertEquals(134, corners.get(2));
		assertEquals(184, corners.get(3));
	}