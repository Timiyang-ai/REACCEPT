public boolean fit( List<Point2D_I32> contour , GrowQueue_I32 input , GrowQueue_I32 output ) {
		this.contour = contour;

		output.reset();
		output.addAll(input);

		computeSegmentEnergy(output);

		double total = 0;
		for (int i = 0; i < output.size(); i++) {
			total += energySegment[i];
		}

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

				optimizeCorners(workCorners1,workCorners2);

				double score = 0;
				for (int j = 0, k = workCorners2.size()-1; j < workCorners2.size(); k=j,j++) {
					score += computeSegmentEnergy(workCorners2,k,j);
				}


				if (score < bestEnergy) {
					betterFound = true;
					bestEnergy = score;
					bestCorners.reset();
					bestCorners.addAll(workCorners2);
				}
			}

			if ( betterFound ) {
				modified = true;
				total = bestEnergy;
				output.reset();
				output.addAll(bestCorners);
			} else {
				break;
			}
		}

		return modified;
	}