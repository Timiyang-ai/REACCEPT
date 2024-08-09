public static List<QuadBlob> copy( List<QuadBlob> input ) {
		List<QuadBlob> output = new ArrayList<QuadBlob>();

		for( QuadBlob i : input ) {
			QuadBlob o = new QuadBlob();
			o.contour = i.contour;
			o.corners = i.corners;
			o.center = i.center;

			o.largestSide = i.largestSide;
			o.smallestSide = i.smallestSide;

			output.add(o);
		}

		// only add connections if they are in the sub-graph
		for( int index = 0; index < input.size(); index++ ) {
			QuadBlob in = input.get(index);
			QuadBlob out = output.get(index);

			for( QuadBlob c : in.conn ) {
				int i = input.indexOf(c);
				if( i >= 0) {
					out.conn.add(output.get(i));
				}
			}
		}

		return output;
	}