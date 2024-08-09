@Test
	public void checkSubImage() {
		List<Point2D_F64> original = new ArrayList<Point2D_F64>();
		List<Point2D_F64> found = new ArrayList<Point2D_F64>();

		detector.detect(image);

		assertTrue(detector.getNumberOfFeatures() > 0);

		for( int i = 0; i < detector.getNumberOfFeatures(); i++ ) {
			Point2D_F64 p = detector.getLocation(i);

			original.add( p.copy() );
		}

		T subimage = BoofTesting.createSubImageOf(image);

		detector.detect(subimage);

		for( int i = 0; i < detector.getNumberOfFeatures(); i++ ) {
			Point2D_F64 p = detector.getLocation(i);

			found.add( p.copy() );
		}

		// see if processing the two images produces the same results
		assertEquals(original.size(),found.size());
		for( int i = 0; i < original.size(); i++ ) {
			Point2D_F64 o = original.get(i);
			Point2D_F64 f = found.get(i);

			assertTrue(o.x == f.x);
			assertTrue(o.y == f.y);
		}
	}