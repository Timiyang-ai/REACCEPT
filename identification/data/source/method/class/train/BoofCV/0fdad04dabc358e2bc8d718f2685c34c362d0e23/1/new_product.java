@SuppressWarnings({"SuspiciousSystemArraycopy"})
	@Override
	public void setTo(T orig) {
		if (orig.width != width || orig.height != height)
			reshape(orig.width,orig.height);
		if (orig.numBands != numBands)
			throw new IllegalArgumentException("The two images have different number of bands");

		if (!orig.isSubimage() && !isSubimage()) {
			System.arraycopy(orig._getData(), orig.startIndex, _getData(), startIndex, stride * height);
		} else {
			int indexSrc = orig.startIndex;
			int indexDst = startIndex;
			for (int y = 0; y < height; y++) {
				System.arraycopy(orig._getData(), indexSrc, _getData(), indexDst, width * numBands);
				indexSrc += orig.stride;
				indexDst += stride;
			}
		}
	}