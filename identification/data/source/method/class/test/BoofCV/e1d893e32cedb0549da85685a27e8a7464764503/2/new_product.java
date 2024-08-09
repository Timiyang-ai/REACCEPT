void considerConnect(SquareNode node0, SquareNode node1) {
		// Find the side on each line which intersects the line connecting the two centers
		lineA.a = node0.center;
		lineA.b = node1.center;

		int intersection0 = findSideIntersect(node0,lineA,lineB);
		int intersection1 = findSideIntersect(node1,lineA,lineB);

		if( intersection1 < 0 || intersection0 < 0 ) {
			return;
		}

		// see if they have a similar shape
		double sideSideRatio0 = node0.largestSide/node0.smallestSideLength();
		double sideSideRatio1 = node1.largestSide/node1.smallestSideLength();

		if( Math.abs(sideSideRatio0-sideSideRatio1) > 1.2 ) {
			return;
		}

		// compare the size of the two closest sides.  They should be similarish
		double closeSide0 = node0.sideLengths[intersection0];
		double closeSide1 = node1.sideLengths[intersection1];
		double ratio = closeSide0>closeSide1  ? closeSide1/closeSide0 : closeSide0/closeSide1;
		if( ratio < 0.5 ) {
			return;
		}

		double distanceApart = lineA.getLength();

		// Checks to see if the two sides selected above are closest to being parallel to each other.
		// Perspective distortion will make the lines not parallel, but will still have a smaller
		// acute angle than the adjacent sides
		if( !mostParallel(node0, intersection0, node1, intersection1)) {
			return;
		}

		// The following two tests see if the end points which define the two selected sides are close to
		// the line created by the end points which define the opposing side.
		// Another way of saying this, for the "top" corner on the side, is it close to the line defined
		// by the side "top" sides on both squares.
		// just look at the code its easier than understanding that description
		if( !areMiddlePointsClose(node0.corners.get(add(intersection0, -1)), node0.corners.get(intersection0),
				node1.corners.get(add(intersection1, 1)), node1.corners.get(add(intersection1, 2)))) {
			return;
		}

		if( !areMiddlePointsClose(node0.corners.get(add(intersection0,2)),node0.corners.get(add(intersection0,1)),
				node1.corners.get(intersection1),node1.corners.get(add(intersection1,-1)))) {
			return;
		}
		checkConnect(node0,intersection0,node1,intersection1,distanceApart);

		// The next 4 tests use point corners on one square to create a line and see if the far corner on the
		// other square is close.  Since all these corners are on a line it should be close.
		// NOTE: the largest side is used to judge distance.  a better way is to use the side the corner is on
//		if( !closeToSide(node1.corners.get(add(intersection1, 1)), node1.corners.get(add(intersection1, 2)),
//				node0.corners.get(add(intersection0, -1)),node0.largestSide) ) {
//			return;
//		}
//
//		if( !closeToSide(node1.corners.get(intersection1),node1.corners.get(add(intersection1,-1)),
//				node0.corners.get(add(intersection0,2)),node0.largestSide) ) {
//			return;
//		}
//
//		if( !closeToSide(node0.corners.get(add(intersection0, -1)),node0.corners.get(intersection0),
//				node1.corners.get(add(intersection1, 2)),node1.largestSide) ) {
//			return;
//		}
//
//		if( closeToSide(node0.corners.get(add(intersection0, 2)),node0.corners.get(add(intersection0,1)),
//				node1.corners.get(add(intersection1,-1)),node1.largestSide) ) {
//			checkConnect(node0,intersection0,node1,intersection1,distanceApart);
//		}
	}