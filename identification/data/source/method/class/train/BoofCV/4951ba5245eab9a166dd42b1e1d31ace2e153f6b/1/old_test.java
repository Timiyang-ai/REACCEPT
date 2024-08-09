@Test
	void selectCorner_NoCornerSquare() {
		ChessboardCornerClusterToGrid alg = new ChessboardCornerClusterToGrid();

		// all corners are candidates
		int idx = alg.selectCorner(createGridInfo(2,2, false));
		assertEquals(0,alg.cornerList.get(idx).index); // 4 non corner squares
		idx = alg.selectCorner(createGridInfo(2,3, false));
		assertEquals(2,alg.cornerList.get(idx).index); // 2 corner squares

		// tell it to only consider only proper corners
		alg.setAllowNoCorner(false);
		idx = alg.selectCorner(createGridInfo(2,2, false));  // 4 non corner squares
		assertEquals(-1,idx);
		idx = alg.selectCorner(createGridInfo(2,3, false));  // 2 corner squares
		assertEquals(2,alg.cornerList.get(idx).index);
	}