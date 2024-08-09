@Override
	public void process(List<EllipseRotated_F64> ellipses , List<List<Node>> clusters ) {

		foundGrids.reset();
		if( clusters.size() == 0 )
			return;

		for (int i = 0; i < clusters.size(); i++) {
			List<Node> cluster = clusters.get(i);
			int clusterSize = cluster.size();
			if( clusterSize < 5 ) // 3 x 3 grid has 5 elements
				continue;

			computeNodeInfo(ellipses, cluster);

			// finds all the nodes in the outside of the cluster
			if( !findContour(true) ) {
				if( verbose ) System.out.println("Contour find failed");
				continue;
			}

			// Find corner to start alignment
			NodeInfo corner = selectSeedCorner();
			if( corner == null ) {
				if( verbose ) System.out.println("No corner found!");
				continue;
			}

			List<List<NodeInfo>> grid = new ArrayList<>();

			// traverse along the axis with closely spaced circles
			double distLeft = corner.distance(corner.left);
			double distRight = corner.distance(corner.right);
			NodeInfo next = distLeft < distRight ? corner.left : corner.right;
			next.marked = true;

//			System.out.println("corner "+corner.ellipse.center);
//			System.out.println("next   "+next.ellipse.center);

			List<NodeInfo> column0 = new ArrayList<>();
			List<NodeInfo> column1 = new ArrayList<>();

			bottomTwoColumns(corner,next,column0,column1);

			if( column0.size() < 2 || column1.size() < 2) {
				if( verbose ) System.out.println("First two columns to small! "+column0.size()+" "+column1.size());
				continue;
			}

			grid.add(column0);
			grid.add(column1);

			boolean error = false;
			boolean even = true;
			while( true ) {
				int expected = column0.size();
				column0 = column1;
				column1 = new ArrayList<>();
				if( !addRemainingColumns(column1,column0,even) )
					break;
				even = !even;
				grid.add(column1);
				if( expected != column1.size() ) {
					error = true;
					if( verbose ) System.out.println("Unexpected column length! "+expected+" "+column1.size());
					break;
				}
			}

			if( !error ) {
				if( grid.size() < 3)
					continue;

				if (checkDuplicates(grid)) {
					if (verbose) System.out.println("contains duplicates");
					continue;
				}

				saveResults(grid);
			}
		}
	}