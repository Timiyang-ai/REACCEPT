@Test
	public void putGridIntoCanonical_rotate() {
		DetectAsymmetricCircleGrid<?> alg = new DetectAsymmetricCircleGrid<>(3,3,null,null,null);

		Grid g = createGrid(3,3);
		List<EllipseRotated_F64> original = new ArrayList<>();
		original.addAll(g.ellipses);

		alg.putGridIntoCanonical(g);
		assertEquals(3,g.rows);
		assertEquals(3,g.columns);
		assertTrue(original.get(0) == g.get(0,0));

		alg.rotateGridCCW(g);
		alg.putGridIntoCanonical(g);
		assertTrue(original.get(0) == g.get(0,0));

		alg.rotateGridCCW(g);
		alg.rotateGridCCW(g);
		alg.putGridIntoCanonical(g);
		assertTrue(original.get(0) == g.get(0,0));

		alg.rotateGridCCW(g);
		alg.rotateGridCCW(g);
		alg.rotateGridCCW(g);
		alg.putGridIntoCanonical(g);
		assertTrue(original.get(0) == g.get(0,0));
	}