public boolean process( T leftImage ) {
		tracker.process(leftImage);

		boolean foundMotion = estimateMotion();

		if( !foundMotion ) {
//			System.out.println("MOTION FAILED!");
			motionFailed++;
		}

//		System.out.println(" numTracksUsed = "+numTracksUsed);
		if( numTracksUsed < MIN_TRACKS ) {
			pixelTo3D.initialize();

			if( bundle != null )
				bundleAdjustment();

			System.out.println("----------- CHANGE KEY FRAME ---------------");
			concatMotion();

			tracker.setKeyFrame();
			tracker.spawnTracks();

			List<PointPoseTrack> tracks = tracker.getPairs();
			List<PointPoseTrack> drop = new ArrayList<PointPoseTrack>();

			// estimate 3D coordinate using stereo vision
			for( PointPoseTrack p : tracks ) {
				Point2D_F64 pixel = p.getPixel().keyLoc;
				// discard point if it can't triangulate
				if( !pixelTo3D.process(pixel.x,pixel.y) || pixelTo3D.getW() == 0 ) {
					drop.add(p);
				} else {
					double w = pixelTo3D.getW();
					p.getLocation().set( pixelTo3D.getX()/w , pixelTo3D.getY()/w, pixelTo3D.getZ()/w);

//					System.out.println("Stereo z = "+p.getLocation().getZ());
//					if( p.getLocation().z < 100 )
//						System.out.println("   * ");
				}
			}

			// drop tracks which couldn't be triangulated
			for( PointPoseTrack p : drop ) {
				tracker.dropTrack(p);
			}

			hasSignificantChange = false;
			motionEstimated = false;
			return foundMotion;
		} else {
			return foundMotion;
		}
	}