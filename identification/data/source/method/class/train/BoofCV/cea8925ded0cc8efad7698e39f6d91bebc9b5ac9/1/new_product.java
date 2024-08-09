public boolean process( ImageUInt8 thresholded )
	{
		// discard old results
		interestPoints = new ArrayList<Point2D_F64>();
		interestSquares = new ArrayList<QuadBlob>();

		// initialize data structures
		binaryA.reshape(thresholded.width,thresholded.height);
		binaryB.reshape(thresholded.width,thresholded.height);

		// filter out small objects
		BinaryImageOps.erode8(thresholded,binaryA);
		BinaryImageOps.erode8(binaryA,binaryB);
		BinaryImageOps.dilate8(binaryB, binaryA);
		BinaryImageOps.dilate8(binaryA,binaryB);

		if( !detectBlobs.process(binaryB) )
			return fail(detectBlobs.getMessage());

		squares = detectBlobs.getDetected();

		// find connections between squares
		ConnectGridSquares.connect(squares);

		// Remove all but the largest islands in the graph to reduce the number of combinations
		List<QuadBlob> squaresPruned = ConnectGridSquares.pruneSmallIslands(squares);
//		System.out.println("Found "+squaresPruned.size()+" blobs");
		
		// given all the blobs, only consider N at one time until a valid target is found
		return shuffleToFindTarget(squaresPruned);
	}