@Test
	public void process() {
		// create a grid in the expected format
		int rows = 4;
		int cols = 3;
		Tuple2<List<Node>,List<EllipseRotated_F64>> grid = createAsymGrid(rows, cols);

		EllipseClustersIntoAsymmetricGrid alg = new EllipseClustersIntoAsymmetricGrid();

		List<List<Node>> nodes = new ArrayList<>();
		nodes.add( grid.data0 );

		alg.process(grid.data1, nodes);

		FastQueue<Grid> found = alg.getGrids();

		assertEquals( 1 , found.size() );

		assertEquals( rows*2 + 1 , found.get(0).rows );
		assertEquals( cols*2 + 1 , found.get(0).columns );
	}