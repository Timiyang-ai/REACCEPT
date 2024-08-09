public void fit( List<Point2D_I32> contour , GrowQueue_I32 input , GrowQueue_I32 output ) {
		this.contour = contour;

		output.reset();
		output.addAll(input);

		computeSegmentEnergy(output);

		double total = 0;
		for (int i = 0; i < output.size(); i++) {
			total += energySegment[i];
		}

		while( output.size() > 3 ) {
			double bestEnergy = total;
			int bestIndex = -1;

			for (int i = 0; i < output.size(); i++) {
				double found = energyRemoveCorner(i, output);
				if (found < bestEnergy) {
					bestEnergy = found;
					bestIndex = i;
				}
			}

			if (bestIndex != -1) {
				output.remove(bestIndex);
				total = bestEnergy;
			} else {
				break;
			}
		}
	}