boolean isClone(String str1, String str2, double thresholdCoefficient) {
		if ((thresholdCoefficient < 0.0) || (thresholdCoefficient > 1.0)) {
			throw new IllegalArgumentException(
					"Threshold Coefficient must be between 0.0 and 1.0!");
		} else
			return LevenshteinDistance.getDefaultInstance().apply(str1, str2) <= getThreshold(
					str1, str2, thresholdCoefficient);
	}