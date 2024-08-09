@Test
	public void connectNodes_twoCluster() {
		List<Polygon2D_F64> squares = new ArrayList<Polygon2D_F64>();
		double width = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				squares.add( createSquare(i*2*width,j*2*width,0,width));
				squares.add( createSquare(i*2*width+100,j*2*width,0,width));
			}
		}

		SquaresIntoClusters alg = new SquaresIntoClusters(1.0);
		alg.computeNodeInfo(squares);
		alg.connectNodes();

		int oneGrid = 2 * 4 + (2 + 4) * 3 + (1 * 2 * 4);
		assertEquals(oneGrid*2, countConnections(alg.nodes.toList()));
	}