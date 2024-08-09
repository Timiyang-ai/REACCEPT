@Test
	public void process() {
		List<Polygon2D_F64> squares = new ArrayList<Polygon2D_F64>();
		double width = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				squares.add( createSquare(i*2*width,j*2*width,0,width));
				squares.add( createSquare(i*2*width+100,j*2*width,0,width));
			}
		}

		SquaresIntoClusters alg = new SquaresIntoClusters(1.0,6);
		List<List<SquareNode>> clusters = alg.process(squares);
		assertEquals(2,clusters.size());

		// second pass, see if it messes up
		clusters = alg.process(squares);
		assertEquals(2,clusters.size());
	}