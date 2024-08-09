private void findLargestAnglesForAllNodes() {
		for (int i = 0; i < listInfo.size(); i++) {
			NodeInfo info = listInfo.get(i);

			if( info.edges.size < 2 )
				continue;

			for (int j = 0, k = info.edges.size-1; j < info.edges.size; k=j,j++) {
				double angleA = info.edges.get(j).angle;
				double angleB = info.edges.get(k).angle;

				double distance = UtilAngle.distanceCCW(angleA,angleB);

				if( distance > info.angleBetween ) {
					info.angleBetween = distance;
					info.left = info.edges.get(j).target;
					info.right = info.edges.get(k).target;
				}
			}
		}
	}