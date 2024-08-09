public boolean fit( List<Point2D_I32> contour , GrowQueue_I32 input , GrowQueue_I32 output ) {

		this.contour = contour;
		output.setTo(input);
		removeDuplicates(output);

		// can't prune a corner and it will still be a polygon
		if( output.size() <= 3 )
			return false;

		computeSegmentEnergy(output);

		double total = 0;
		for (int i = 0; i < output.size(); i++) {
			total += energySegment[i];
		}

		FitLinesToContour fit = new FitLinesToContour();
		fit.setContour(contour);

		boolean modified = false;
		while( output.size() > 3 ) {
			double bestEnergy = total;
			boolean betterFound = false;
			bestCorners.reset();

			for (int i = 0; i < output.size(); i++) {
				// add all but the one which was removed
				workCorners1.reset();
				for (int j = 0; j < output.size(); j++) {
					if( i != j ) {
						workCorners1.add(output.get(j));
					}
				}

				// just in case it created a duplicate
				removeDuplicates(workCorners1);// todo optimize
				if( workCorners1.size() > 3 ) {

					int anchor0 = CircularIndex.addOffset(i, -2, workCorners1.size());
					int anchor1 = CircularIndex.addOffset(i, 1, workCorners1.size());

					if (fit.fitAnchored(anchor0, anchor1, workCorners1, workCorners2)) {

						double score = 0;
						for (int j = 0, k = workCorners2.size() - 1; j < workCorners2.size(); k = j, j++) {
							score += computeSegmentEnergy(workCorners2, k, j);
						}

						if (score < bestEnergy) {
							betterFound = true;
							bestEnergy = score;
							bestCorners.reset();
							bestCorners.addAll(workCorners2);
						}
					}
				}
			}

			if ( betterFound ) {
				modified = true;
				total = bestEnergy;
				output.setTo(bestCorners);
			} else {
				break;
			}
		}

		return modified;
	}