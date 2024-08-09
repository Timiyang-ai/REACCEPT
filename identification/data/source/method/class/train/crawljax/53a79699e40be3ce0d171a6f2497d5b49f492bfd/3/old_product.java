boolean isClone(String str1, String str2, double thresholdCoef) {
		if ((thresholdCoef < 0.0) || (thresholdCoef > 1.0)) {
			throw new IllegalArgumentException(
					"Threshold Coefficient must be between 0.0 and 1.0!");
		} else if (StringUtils.getLevenshteinDistance(str1, str2) <= getThreshold(
				str1, str2, thresholdCoef)) {
			return true;
		} else {
			return false;
		}
	}