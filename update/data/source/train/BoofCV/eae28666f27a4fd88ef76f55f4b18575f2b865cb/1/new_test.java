@Test
	public void process() {

		EllipseClustersIntoHexagonalGrid alg = new EllipseClustersIntoHexagonalGrid();

//		process(3, 3, alg); // too small. Simplies the code by igniring this case
		process(4, 3, alg);
		process(4, 4, alg);
		process(4, 5, alg);
		process(5, 5, alg);
		process(5, 8, alg);
	}