@SuppressWarnings({"SuspiciousNameCombination"})
	public void setDescription(KltFeature feature) {
		if (derivX == null || derivY == null)
			throw new IllegalArgumentException("Image derivatives must be set");

		setAllowedBounds(feature);
		int regionWidth = feature.radius * 2 + 1;
		int size = regionWidth * regionWidth;

		if (!isFullyInside(feature.x, feature.y))
			throw new IllegalArgumentException("Feature is too close to the image's border");

		float tl_x = feature.x - feature.radius;
		float tl_y = feature.y - feature.radius;

		interpInput.region(tl_x, tl_y, feature.pixel, regionWidth, regionWidth);
		interpDeriv.setImage(derivX);
		interpDeriv.region(tl_x, tl_y, feature.derivX, regionWidth, regionWidth);
		interpDeriv.setImage(derivY);
		interpDeriv.region(tl_x, tl_y, feature.derivY, regionWidth, regionWidth);

		float Gxx = 0, Gyy = 0, Gxy = 0;
		for (int i = 0; i < size; i++) {
			float dX = feature.derivX[i];
			float dY = feature.derivY[i];

			Gxx += dX * dX;
			Gyy += dY * dY;
			Gxy += dX * dY;
		}

		feature.Gxx = Gxx;
		feature.Gyy = Gyy;
		feature.Gxy = Gxy;
	}