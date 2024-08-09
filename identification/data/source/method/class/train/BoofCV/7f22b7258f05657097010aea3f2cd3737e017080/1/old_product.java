public static List<PointIndex_I32> fitPolygon(List<Point2D_I32> sequence, boolean loop,
												  double splitFraction, ConfigLength minimumSideLength, int iterations) {
		GrowQueue_I32 splits = new GrowQueue_I32();

		if( loop ) {
			SplitMergeLineFitLoop alg = new SplitMergeLineFitLoop(splitFraction,minimumSideLength,iterations);
			alg.process(sequence,splits);
			RefinePolyLineCorner refine = new RefinePolyLineCorner(true,10);
			refine.fit(sequence,splits);
		} else {
			SplitMergeLineFitSegment alg = new SplitMergeLineFitSegment(splitFraction,minimumSideLength,iterations);
			alg.process(sequence,splits);
			RefinePolyLineCorner refine = new RefinePolyLineCorner(false,10);
			refine.fit(sequence,splits);
		}

		FastQueue<PointIndex_I32> output = new FastQueue<>(PointIndex_I32.class, true);
		indexToPointIndex(sequence,splits,output);

		return new ArrayList<>(output.toList());
	}