void considerConnect(SquareNode node0, SquareNode node1) {

		// Find the side on each line which intersects the line connecting the two centers
		lineA.a = node0.center;
		lineA.b = node1.center;

		int intersection0 = graph.findSideIntersect(node0,lineA,intersection,lineB);
		connectLine.a.set(intersection);
		int intersection1 = graph.findSideIntersect(node1,lineA,intersection,lineB);
		connectLine.b.set(intersection);

		if( intersection1 < 0 || intersection0 < 0 ) {
			return;
		}

		double side0 = node0.sideLengths[intersection0];
		double side1 = node1.sideLengths[intersection1];

		// it shuold intersect about in the middle of the line

		double sideLoc0 = connectLine.a.distance(node0.square.get(intersection0))/side0;
		double sideLoc1 = connectLine.b.distance(node1.square.get(intersection1))/side1;

		if( Math.abs(sideLoc0-0.5)>0.35 || Math.abs(sideLoc1-0.5)>0.35 )
			return;

		// seems to be about 1/2 the length typically
//		System.out.println("lineA "+lineA.getLength()+"  connectLine "+connectLine.getLength());

		// see if the spacing makes sense
		double distanceApart = connectLine.getLength()*spaceToSquareRatio;

//		if( distanceApart*1.2 < Math.min(side0,side1) )
//			return;
//		if( distanceApart*0.8 > Math.max(side0,side1) )
//			return;

		// see if connecting sides are of similar size
		if( Math.abs(side0-side1)/Math.max(side0,side1) > 0.25 ) {
			return;
		}
//
//		// see if the intersection line is about perpendicular to both sides
//		double angle0 = acuteAngle(node0,intersection0,lineA);
//		double angle1 = acuteAngle(node1,intersection1,lineA);
//
//		System.out.printf("  acute %5.2f   %5.2f\n",UtilAngle.degree(angle0),UtilAngle.degree(angle1));

//		double angle = UtilAngle.radian(20);
//		if( Math.abs(angle0-Math.PI/2) > angle || Math.abs(angle1-Math.PI/2) > angle)
//			return;


		// Checks to see if the two sides selected above are closest to being parallel to each other.
		// Perspective distortion will make the lines not parallel, but will still have a smaller
		// acute angle than the adjacent sides
		if( !graph.almostParallel(node0, intersection0, node1, intersection1)) {
			return;
		}

		double ratio = Math.max(node0.smallestSide/node1.largestSide ,
				node1.smallestSide/node0.largestSide);

//		System.out.println("ratio "+ratio);
		if( ratio > 1.3 )
			return;

		// See if they are crudely the same size
//		double area0 = node0.corners.areaSimple();
//		double area1 = node1.corners.areaSimple();

//		if( Math.min(area0,area1)/Math.max(area0,area1) < 0.25 )
//			return;

		// The following two tests see if the end points which define the two selected sides are close to
		// the line created by the end points which define the opposing side.
		// Another way of saying this, for the "top" corner on the side, is it close to the line defined
		// by the side "top" sides on both squares.
		// just look at the code its easier than understanding that description
//		if( !areMiddlePointsClose(node0.corners.get(add(intersection0, -1)), node0.corners.get(intersection0),
//				node1.corners.get(add(intersection1, 1)), node1.corners.get(add(intersection1, 2)))) {
//			return;
//		}
//
//		if( !areMiddlePointsClose(node0.corners.get(add(intersection0,2)),node0.corners.get(add(intersection0,1)),
//				node1.corners.get(intersection1),node1.corners.get(add(intersection1,-1)))) {
//			return;
//		}
		graph.checkConnect(node0,intersection0,node1,intersection1,distanceApart);
	}