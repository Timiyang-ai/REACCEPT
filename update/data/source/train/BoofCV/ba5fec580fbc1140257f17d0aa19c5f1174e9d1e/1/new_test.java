@Test
	public void sanityCheckConvex_positive() {
		List<Point2D_I32> contour = rect(5,6,12,20);

		for (int i = 0; i < contour.size(); i++) {
			int farthest = -1;
			double distance = -1;

			for (int j = 0; j < contour.size(); j++) {
				double d = contour.get(i).distance(contour.get(j));
				if( d > distance ) {
					distance = d;
					farthest = j;
				}
			}

			assertTrue( PolylineSplitMerge.sanityCheckConvex(contour,i,farthest));
		}
	}