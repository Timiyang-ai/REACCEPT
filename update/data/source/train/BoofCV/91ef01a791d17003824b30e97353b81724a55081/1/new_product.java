public boolean convert( ChessboardCornerGraph cluster , GridInfo info ) {
		// default to an invalid value to ensure a failure doesn't go unnoticed.
		info.reset();

		// Get the edges in a consistent order
		if( !orderEdges(cluster) )
			return false;

		// Now we need to order the nodes into a proper grid which follows right hand rule
		if( !orderNodes(cluster.corners,info) )
			return false;

		// select a valid corner to be (0,0). If there are multiple options select the one which is
		int corner = selectCorner(info);
		if( corner == -1 ) {
			if( verbose != null) verbose.println("Failed to find valid corner.");
			return false;
		}
		// rotate the grid until the select corner is at (0,0)
		for (int i = 0; i < corner; i++) {
			rotateCCW(info);
		}

		return true;
	}