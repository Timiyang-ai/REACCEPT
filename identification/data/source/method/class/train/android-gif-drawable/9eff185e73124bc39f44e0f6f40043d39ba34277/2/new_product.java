public void setInSampleSize(@IntRange(from = 1, to = Character.MAX_VALUE) int inSampleSize) {
		if (inSampleSize < 1 || inSampleSize > Character.MAX_VALUE) {
			this.inSampleSize = 1;
		} else {
			this.inSampleSize = (char) inSampleSize;
		}
	}