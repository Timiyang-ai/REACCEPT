void computeJointHistogram(GrayU8 left, GrayU8 right, int minDisparity , GrayU8 disparity, int invalid) {
		// zero the histogram
		ImageMiscOps.fill(histJoint,0);

		int histLength = histogramIntensity.length;

		// Compute the joint histogram
		for (int row = 0; row < left.height; row++) {
			int idx = row*left.stride;
			for (int col = 0; col < left.width; col++, idx++ ) {
				int d = disparity.data[idx]&0xFF;
				// Don't consider pixels without correspondences
				if( d == invalid )
					continue;

				d += minDisparity;

				// NOTE: Paper says to take care to avoid double mappings due to occlusions. Not sure I'm doing that.

				// The equation below assumes that all disparitys are valid and won't result in a pixel going
				// outside the image
				int leftValue  =  left.data[idx  ]&0xFF; // I(x  ,y)
				int rightValue = right.data[idx-d]&0xFF; // I(x-d,y)

				// scale the pixel intensity for the histogram
				leftValue  = scalePixelValue(leftValue);
				rightValue = scalePixelValue(rightValue);

				// increment the histogram
				histJoint.data[leftValue*histLength+rightValue]++; // H(L,R) += 1
			}
		}
	}