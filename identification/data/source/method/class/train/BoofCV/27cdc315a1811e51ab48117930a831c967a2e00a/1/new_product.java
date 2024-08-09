static void bottomTwoColumns(NodeInfo first, NodeInfo second, List<NodeInfo> column0, List<NodeInfo> column1) {
		column0.add(first);
		column0.add(second);

		NodeInfo a = selectClosestN(first,second);
		if( a == null ) {
			return;
		}
		a.marked = true;
		column1.add(a);
		NodeInfo b = second;

		while( true ) {
			NodeInfo t = selectClosestN(b,a);
			if( t == null ) break;
			t.marked = true;
			column1.add(t);

			a = t;
			t = selectClosestN(b,a);
			if( t == null ) break;
			t.marked = true;
			column0.add(t);
			b = t;
		}
	}