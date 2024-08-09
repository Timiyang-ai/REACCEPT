@Test
	public void process_simple() {
		SquaresIntoCrossClusters alg = new SquaresIntoCrossClusters(0.05, -1);

		List<Polygon2D_F64> squares = new ArrayList<Polygon2D_F64>();
		squares.add( createSquare(7,8));
		squares.add( createSquare(9,8));
		squares.add( createSquare(8,9));
		squares.add( createSquare(7,10));
		squares.add( createSquare(9,10));

		List<BinaryPolygonDetector.Info> squareInfo = createInfo(squares);

		List<List<SquareNode>> clusters = alg.process(squares,squareInfo);

		assertEquals(1,clusters.size());

		List<SquareNode> cluster = clusters.get(0);

		int connections[] = new int[5];
		for( SquareNode n : cluster ) {
			connections[n.getNumberOfConnections()]++;
		}

		assertEquals(0,connections[0]);
		assertEquals(4,connections[1]);
		assertEquals(0,connections[2]);
		assertEquals(0,connections[3]);
		assertEquals(1,connections[4]);
	}