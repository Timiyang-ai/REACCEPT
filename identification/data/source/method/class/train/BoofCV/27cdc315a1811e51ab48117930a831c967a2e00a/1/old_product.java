static void bottomTwoColumns(NodeInfo corner, List<NodeInfo> column0, List<NodeInfo> column1) {
		column0.add(corner);
		column0.add(corner.right);

		NodeInfo a = selectClosest(corner.right,corner).target;
		if( a == null ) {
			return;
		}
		a.marked = true;
		column1.add(a);
		NodeInfo b = corner.right;

		corner.marked = true;
		corner.right.marked = true;

		while( true ) {
			NodeInfo t = selectClosest(b,a).target;
			if( t == null ) break;
			t.marked = true;
			column1.add(t);

			a = t;
			t = selectClosest(b,a).target;
			if( t == null ) break;
			t.marked = true;
			column0.add(t);
			b = t;
		}
	}