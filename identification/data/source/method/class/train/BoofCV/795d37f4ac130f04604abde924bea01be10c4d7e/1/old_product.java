public static List<SquareBlob> copy( List<SquareBlob> input ) {
		List<SquareBlob> output = new ArrayList<SquareBlob>();

		for( SquareBlob i : input ) {
			SquareBlob o = new SquareBlob();
			o.contour = i.contour;
			o.corners = i.corners;
			o.center = i.center;

			o.largestSide = i.largestSide;
			o.smallestSide = i.smallestSide;

			output.add(o);
		}

		// only add connections if they are in the sub-graph
		for( int index = 0; index < input.size(); index++ ) {
			SquareBlob in = input.get(index);
			SquareBlob out = output.get(index);

			for( SquareBlob c : in.conn ) {
				int i = input.indexOf(c);
				if( i >= 0) {
					out.conn.add(output.get(i));
				}
			}
		}

		return output;
	}