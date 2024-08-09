@Override
	protected void vertical( GrayF32 intensity ) {
		float[] hXX = horizXX.data;
		float[] hXY = horizXY.data;
		float[] hYY = horizYY.data;
		final float[] inten = intensity.data;

		final int imgHeight = horizXX.getHeight();
		final int imgWidth = horizXX.getWidth();

		final int kernelWidth = radius * 2 + 1;

		final int startX = radius;
		final int endX = imgWidth - radius;

		final int backStep = kernelWidth * imgWidth;

		for (x = startX; x < endX; x++) {
			int srcIndex = x;
			int destIndex = imgWidth * radius + x;
			totalXX = totalXY = totalYY = 0;

			int indexEnd = srcIndex + imgWidth * kernelWidth;
			for (; srcIndex < indexEnd; srcIndex += imgWidth) {
				totalXX += hXX[srcIndex];
				totalXY += hXY[srcIndex];
				totalYY += hYY[srcIndex];
			}

			tempXX[x] = totalXX;
			tempXY[x] = totalXY;
			tempYY[x] = totalYY;

			y = radius;
			// compute the eigen values
			inten[destIndex] = computeIntensity();
			destIndex += imgWidth;
			y++;
		}

		// change the order it is processed in to reduce cache misses
		for (y = radius + 1; y < imgHeight - radius; y++) {
			int srcIndex = (y + radius) * imgWidth + startX;
			int destIndex = y * imgWidth + startX;

			for (x = startX; x < endX; x++, srcIndex++, destIndex++) {
				totalXX = tempXX[x] - hXX[srcIndex - backStep];
				tempXX[x] = totalXX += hXX[srcIndex];
				totalXY = tempXY[x] - hXY[srcIndex - backStep];
				tempXY[x] = totalXY += hXY[srcIndex];
				totalYY = tempYY[x] - hYY[srcIndex - backStep];
				tempYY[x] = totalYY += hYY[srcIndex];

				inten[destIndex] = computeIntensity();
			}
		}
	}