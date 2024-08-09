@Test
	public void considerConnect_nominal() {
		List<Polygon2D_F64> squares = new ArrayList<>();
		squares.add(new Polygon2D_F64(-1, 1, 1, 1, 1, -1, -1, -1));
		squares.add(new Polygon2D_F64(2, 1, 4, 1, 4, -1, 2, -1));

		SquaresIntoRegularClusters alg = new SquaresIntoRegularClusters(2, 6, 1.35);
		alg.computeNodeInfo(squares);
		SquareNode a = alg.nodes.get(0);
		SquareNode b = alg.nodes.get(1);

		alg.considerConnect(a, b);
		assertConnected(a, 1, b, 3, 3);
	}