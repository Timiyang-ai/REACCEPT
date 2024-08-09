@Test
	void convert_randomized() {
		ChessboardCornerClusterToGrid alg = new ChessboardCornerClusterToGrid();
		alg.setAllowNoCorner(false);

		// do a few loops to test more random cases
		for (int i = 0; i < 10; i++) {
			convert(alg,2,2,true);
			convert(alg,2,3,true);
			convert(alg,3,2,true);
			convert(alg,3,3,true);
			convert(alg,3,4,true);
			convert(alg,4,4,true);
		}
	}