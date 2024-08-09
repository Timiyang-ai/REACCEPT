@Test
	public void bottomTwoColumns_case1() {
		List<EllipseRotated_F64> ellipses = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			ellipses.add(new EllipseRotated_F64(i, 0, 1, 1, 0));
			ellipses.add(new EllipseRotated_F64(i, 0.866 * 2, 1, 1, 0));
			if (i == 0) {
				ellipses.add(new EllipseRotated_F64(i + 0.5, 0.866, 1, 1, 0));
				ellipses.add(new EllipseRotated_F64(i + 0.5, 1.732, 1, 1, 0));
			}
		}

		Tuple2<List<Node>,List<EllipseRotated_F64>> input = connectEllipses(ellipses,1.1);

		EllipseClustersIntoHexagonalGrid alg = new EllipseClustersIntoHexagonalGrid();
		alg.computeNodeInfo(input.data1,input.data0);
		alg.findContour(true);

		List<NodeInfo> column0 = new ArrayList<>();
		List<NodeInfo> column1 = new ArrayList<>();

		NodeInfo corner = alg.listInfo.get(0);

		alg.bottomTwoColumns(corner, column0, column1);

		assertEquals(2,column0.size());
		assertEquals(1,column1.size());

		assertTrue(column0.get(0).ellipse.center.distance(0,0) < 1e-4);
		assertTrue(column0.get(1).ellipse.center.distance(1,0) < 1e-4);
		assertTrue(column1.get(0).ellipse.center.distance(0.5,0.866) < 1e-4);
	}