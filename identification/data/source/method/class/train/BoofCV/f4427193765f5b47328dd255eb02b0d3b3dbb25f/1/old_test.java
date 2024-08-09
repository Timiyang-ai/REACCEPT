@Test
	public void process() {
		List<Point2D_I32> contour = new ArrayList<Point2D_I32>();
		
		for( int i = 0; i < 10; i++ ) {
			contour.add( new Point2D_I32(i,0));
			contour.add( new Point2D_I32(i,9));
		}
		for( int i = 1; i < 9; i++ ) {
			contour.add( new Point2D_I32(0,i));
			contour.add( new Point2D_I32(9,i));
		}

		// remove any structure from the input
		Collections.shuffle(contour,new Random(1234));
		
		FindQuadCorners alg = new FindQuadCorners(0.5);

		List<Point2D_I32> corners = alg.process(contour);

		// check the solution and make sure its in the correct order
		Point2D_I32 a = corners.get(0);
		Point2D_I32 b = corners.get(1);
		Point2D_I32 c = corners.get(2);
		Point2D_I32 d = corners.get(3);

		assertEquals(0,a.x);
		assertEquals(0,a.y);

		assertEquals(9,b.x);
		assertEquals(0,b.y);

		assertEquals(9,c.x);
		assertEquals(9,c.y);

		assertEquals(0,d.x);
		assertEquals(9,d.y);
	}