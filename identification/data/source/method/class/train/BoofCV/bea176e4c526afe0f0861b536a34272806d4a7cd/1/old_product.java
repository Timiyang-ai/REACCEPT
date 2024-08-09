protected void processFeatureCandidate( int x , int y , float value ,boolean maximum ) {
		// suppress response along edges
		if( isEdge(x,y) )
			return;

		// Estimate the scale and 2D point by fitting 2nd order polynomials
		// This is different from the original paper
		float signAdj = maximum ? 1 : -1;

		float x0 = dogTarget.unsafe_get(x - 1, y)*signAdj;
		float x2 = dogTarget.unsafe_get(x + 1, y)*signAdj;
		float y0 = dogTarget.unsafe_get(x , y - 1)*signAdj;
		float y2 = dogTarget.unsafe_get(x , y + 1)*signAdj;

		float s0 = dogLower.unsafe_get(x , y )*signAdj;
		float s2 = dogUpper.unsafe_get(x , y )*signAdj;

		ScalePoint p = detections.grow();

		// Compute the interpolated coordinate of the point in the original image coordinates
		p.x = pixelScaleToInput*(x + polyPeak(x0, value, x2));
		p.y = pixelScaleToInput*(y + polyPeak(y0, value, y2));

		// find the peak then do bilinear interpolate between the two appropriate sigmas
		double sigmaInterp = polyPeak(s0, value, s2); // scaled from -1 to 1
		if( sigmaInterp < 0 ) {
			p.scale = sigmaLower*(-sigmaInterp) + (1+sigmaInterp)*sigmaTarget;
		} else {
			p.scale = sigmaUpper*sigmaInterp + (1-sigmaInterp)*sigmaTarget;
		}

		// a maximum corresponds to a dark object and a minimum to a whiter object
		p.white = !maximum;

		handleDetection(p);
	}