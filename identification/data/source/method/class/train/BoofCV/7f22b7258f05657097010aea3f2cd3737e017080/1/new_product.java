public static List<PointIndex_I32> fitPolygon(List<Point2D_I32> sequence, boolean loop,
												  int minimumSideLength , double cornerPenalty ) {

		PolylineSplitMerge alg = new PolylineSplitMerge();
		alg.setLoops(loop);
		alg.setMinimumSideLength(minimumSideLength);
		alg.setCornerScorePenalty(cornerPenalty);

		alg.process(sequence);
		PolylineSplitMerge.CandidatePolyline best = alg.getBestPolyline();

		FastQueue<PointIndex_I32> output = new FastQueue<>(PointIndex_I32.class, true);
		if( best != null ) {
			indexToPointIndex(sequence,best.splits,output);
		}

		return new ArrayList<>(output.toList());
	}