@Test
	public void selectCornerToRemove() {
		List<Point2D_I32> contour = rect(10,12,20,18);

		PolylineSplitMerge alg = new PolylineSplitMerge();
		alg.setCornerScorePenalty(0.5);
		alg.addCorner(0);
		alg.addCorner(5);  // point less corner.
		alg.addCorner(10);
		alg.addCorner(16);
		alg.addCorner(26);

		Element<Corner> expected = alg.list.getHead().next;
		PolylineSplitMerge.ErrorValue foundError = new PolylineSplitMerge.ErrorValue();
		Element<Corner> found = alg.selectCornerToRemove(contour,foundError);

		assertTrue(expected == found);
		assertEquals(0,foundError.value, GrlConstants.TEST_F64);

	}