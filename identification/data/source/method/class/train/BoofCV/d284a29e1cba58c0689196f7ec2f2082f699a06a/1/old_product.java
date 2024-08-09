private void computeLocalGradient(int cx, int cy) {
		// Find the local region that it will search inside of while avoiding image border
		region.set(cx-fitRadius,cy-fitRadius, cx + fitRadius+1,cy + fitRadius+1);
		BoofMiscOps.boundRectangleInside(image, region);

		double dRadius = (double)fitRadius;

		// compute the gradient at each pixel while taking in account the ignore radius
		// scale the coordinate so that they range from -1 to 1 for numerical reasons.  not sure if neccisary
		// but doesn't really hurt
		for (int y = region.y0; y < region.y1; y++) {
			if( Math.abs(y-cy) > ignoreRadius )
				continue;
			double scaledY = (y-cy)/dRadius;
			for (int x = region.x0; x < region.x1; x++) {
				if( Math.abs(x-cx) > ignoreRadius )
					continue;

				GradientValue v = gradient.compute(x,y);
				double scaledX = (x-cx)/dRadius;

				points.grow().set(scaledX,scaledY,v.getX(),v.getY());
			}
		}
	}