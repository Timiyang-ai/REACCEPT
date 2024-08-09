@Test
	public void increaseNumberOfSidesByOne() {
		List<Point2D_I32> contour = rect(10,12,20,22);

		PolylineSplitMerge alg = new PolylineSplitMerge();
		alg.addCorner(5);
		alg.addCorner(10);
		alg.addCorner(20);
		alg.addCorner(30);

		// set up polyline variables
		Element<Corner> e = alg.list.getHead();
		while( e != null ) {
			e.object.splitable = false;
			e = e.next;
		}
		e = alg.list.getTail();
		e.object.splitable = true;
		e.object.sideError = alg.computeSideError(contour,e.object.index,5);
		alg.setSplitVariables(contour,e,alg.list.getHead());


		assertTrue(alg.increaseNumberOfSidesByOne(contour));

		assertEquals(4,alg.list.size());
		e = alg.list.getHead();
		assertEquals(10,e.object.index);e = e.next;
		assertEquals(20,e.object.index);e = e.next;
		assertEquals(30,e.object.index);e = e.next;
		assertEquals(0,e.object.index);
	}