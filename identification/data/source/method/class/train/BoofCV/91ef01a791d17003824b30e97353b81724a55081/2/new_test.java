@Test
	void sortEdgesCCW_shift() {
		FastQueue<Node> corners = new FastQueue<>(Node.class,true);

		// Add nodes with 3 edges
		for (int jump = 0; jump < 3; jump++) {
			Node target = corners.grow();
			target.x = 10;
			target.y = 12;

			for (int i = 0; i < 3; i++) {
				double theta = i*Math.PI/2;
				if( i > jump )
					theta += Math.PI/2;
				double c = Math.cos(theta);
				double s = Math.sin(theta);

				double r = 4;
				Node n = new Node();
				n.x = target.x + r*c;
				n.y = target.y + r*s;
				target.edges[i] = n;
			}
			// shuffle to make it a better test
			shuffle(target.edges);
		}

		// add Nodes with two edges
		for (int count = 0; count < 10; count++) {
			Node target = corners.grow();
			target.x = 10;
			target.y = 12;

			for (int i = 0; i < 2; i++) {
				double theta = i*Math.PI/2;
				double c = Math.cos(theta);
				double s = Math.sin(theta);

				double r = 4;
				Node n = new Node();
				n.x = target.x + r*c;
				n.y = target.y + r*s;
				target.edges[i] = n;
			}
			// shuffle to make it a better test
			shuffle(target.edges);
		}

		ChessboardCornerClusterToGrid alg = new ChessboardCornerClusterToGrid();

		alg.sortEdgesCCW(corners);

		for( Node n : corners.toList() ) {
			Node m0 = n.edges[0];
			double theta0 = Math.atan2(m0.y-n.y,m0.x-n.x);

			for (int i = 0; i < 4; i++) {
				Node m = n.edges[i];
				if( m == null )
					continue;

				double thetaI = Math.atan2(m.y-n.y,m.x-n.x);

				assertEquals(i*Math.PI/2.0,UtilAngle.distanceCCW(theta0,thetaI),0.001);
			}
		}
	}