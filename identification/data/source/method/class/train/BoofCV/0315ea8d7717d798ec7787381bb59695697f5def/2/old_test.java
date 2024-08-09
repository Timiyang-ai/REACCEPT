@Test
	public void fitEllipse_F64() {
		EllipseRotated_F64 rotated = new EllipseRotated_F64(1,2,3,2,-0.05);

		List<Point2D_F64> points = new ArrayList<Point2D_F64>();
		for( int i = 0; i < 20; i++ ) {
			double theta = 2.0*(double)Math.PI*i/20;
			points.add(UtilEllipse_F64.computePoint(theta, rotated, null));
		}

		EllipseRotated_F64 found = ShapeFittingOps.fitEllipse_F64(points,0,false,null).shape;

		assertEquals(rotated.center.x,found.center.x,1e-8);
		assertEquals(rotated.center.y,found.center.y,1e-8);
		assertEquals(rotated.a,found.a,1e-8);
		assertEquals(rotated.b,found.b,1e-8);
		assertEquals(rotated.phi,found.phi,1e-8);

		// make sure refinement doesn't skew it up
		found = ShapeFittingOps.fitEllipse_F64(points,20,false,null).shape;

		assertEquals(rotated.center.x,found.center.x,1e-8);
		assertEquals(rotated.center.y,found.center.y,1e-8);
		assertEquals(rotated.a,found.a,1e-8);
		assertEquals(rotated.b,found.b,1e-8);
		assertEquals(rotated.phi,found.phi,1e-8);
	}