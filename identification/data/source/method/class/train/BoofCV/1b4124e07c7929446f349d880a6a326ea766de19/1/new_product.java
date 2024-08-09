public void process( T gray ) {
		binary.reshape(gray.width,gray.height);

		inputToBinary.process(gray,binary);
		squareDetector.process(gray,binary);
		// These are in undistorted pixels
		FastQueue<Polygon2D_F64> candidates = squareDetector.getFoundPolygons();

		found.reset();

		if( verbose ) System.out.println("---------- Got Polygons! "+candidates.size);

		for (int i = 0; i < candidates.size; i++) {
			// compute the homography from the input image to an undistorted square image
			Polygon2D_F64 p = candidates.get(i);

			// REMOVE EVENTUALLY  This is a hack around how interpolation is performed
			// Using a surface integral instead would remove the need for this.  Basically by having it start
			// interpolating from the lower extent it samples inside the image more
			// A good unit test to see if this hack is no longer needed is to rotate the order of the polygon and
			// see if it returns the same undistorted image each time
			double best=Double.MAX_VALUE;
			for (int j = 0; j < 4; j++) {
				double found = p.get(0).normSq();
				if( found < best ) {
					best = found;
					interpolationHack.set(p);
				}
				UtilPolygons2D_F64.shiftDown(p);
			}

			UtilPolygons2D_F64.convert(interpolationHack,q);

			// remember, visual clockwise isn't the same as math clockwise, hence
			// counter clockwise visual to the clockwise quad
			pairsRemovePerspective.get(0).set(0, 0, q.a.x, q.a.y);
			pairsRemovePerspective.get(1).set( square.width ,      0        , q.b.x , q.b.y );
			pairsRemovePerspective.get(2).set( square.width , square.height , q.c.x , q.c.y );
			pairsRemovePerspective.get(3).set( 0            , square.height , q.d.x , q.d.y );

			if( !computeHomography.process(pairsRemovePerspective,H) ) {
				if( verbose ) System.out.println("rejected initial homography");
				continue;
			}

			// refine homography estimate
			if( !refineHomography.fitModel(pairsRemovePerspective,H,H_refined) ) {
				if( verbose ) System.out.println("rejected refine homography");
				continue;
			}

			// pass the found homography onto the image transform
			UtilHomography.convert(H_refined, transformHomography.getModel());

			// TODO Improve how perspective is removed
			// The current method introduces artifacts.  If the "square" is larger
			// than the detected region and bilinear interpolation is used then pixels outside will// influence the
			// value of pixels inside and shift things over.  this is all bad

			// remove the perspective distortion and process it
			removePerspective.apply(gray, square);

			BinaryPolygonDetector.Info info = squareDetector.getPolygonInfo().get(i);

			// see if the black border is actually black
			if( minimumBorderBlackFraction > 0 ) {
				double pixelThreshold = (info.edgeInside + info.edgeOutside) / 2;
				double foundFraction = computeFractionBoundary((float) pixelThreshold);
				if( foundFraction < minimumBorderBlackFraction ) {
					if( verbose ) System.out.println("rejected black border fraction "+foundFraction);
					continue;
				}
			}
			if( processSquare(square,result,info.edgeInside,info.edgeOutside)) {
				prepareForOutput(q,result);

				if( verbose ) System.out.println("accepted!");
			} else {
				if( verbose ) System.out.println("rejected process square");
			}
		}
	}