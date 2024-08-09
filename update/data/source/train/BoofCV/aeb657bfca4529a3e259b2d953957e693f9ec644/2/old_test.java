@Test
	public void findInitialTriangle() {
		List<Point2D_I32> contour = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			contour.add(new Point2D_I32(i,i));
		}
		for (int i = 0; i < 10; i++) {
			contour.add(new Point2D_I32(9-i,9));
		}
		for (int i = 0; i < 8; i++) {
			contour.add(new Point2D_I32(0,8-i));
		}

		PolylineSplitMerge alg = new PolylineSplitMerge();

		assertTrue(alg.findInitialTriangle(contour));

		assertEquals(3,alg.list.size());

		// the order was specially selected knowing what the current algorithm is
		// te indexes are what it should be no matter what
		Element<Corner> e = alg.list.getHead();
		assertEquals(9,e.object.index);e = e.next;
		assertEquals(19,e.object.index);e = e.next;
		assertEquals(0,e.object.index);
	}