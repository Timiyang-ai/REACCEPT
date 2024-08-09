@Test
	public void addEdgesToInfo_AND_findLargestAnglesForAllNodes() {
		EllipseClustersIntoAsymmetricGrid alg = new EllipseClustersIntoAsymmetricGrid();

		setNodeInfo(alg.listInfo.grow(), 0 , 0);
		setNodeInfo(alg.listInfo.grow(),-1 , 0);
		setNodeInfo(alg.listInfo.grow(), 3 , 1);
		setNodeInfo(alg.listInfo.grow(), 0 , 1);
		setNodeInfo(alg.listInfo.grow(), 1 , 2);

		List<Node> cluster = new ArrayList<>();
		cluster.add( createNode(0, 1,2,3));
		cluster.add( createNode(1, 0,2,4));
		cluster.add( createNode(2, 0,1));
		cluster.add( createNode(3, 0));
		cluster.add( createNode(4));

		alg.addEdgesToInfo(cluster);

		checkEdgeInfo(alg.listInfo.get(0), 3);
		checkEdgeInfo(alg.listInfo.get(1), 3);
		checkEdgeInfo(alg.listInfo.get(2), 2);
		checkEdgeInfo(alg.listInfo.get(3), 1);
		checkEdgeInfo(alg.listInfo.get(4), 0);

		// check results against hand selected solutions
		alg.findLargestAnglesForAllNodes();

		checkLargestAngle(alg.listInfo.get(0),alg.listInfo.get(1),alg.listInfo.get(2));
		checkLargestAngle(alg.listInfo.get(1),alg.listInfo.get(4),alg.listInfo.get(0));
		checkLargestAngle(alg.listInfo.get(2),alg.listInfo.get(0),alg.listInfo.get(1));
		checkLargestAngle(alg.listInfo.get(3),null,null);
		checkLargestAngle(alg.listInfo.get(4),null,null);

	}