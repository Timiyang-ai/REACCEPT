static
	public boolean acceptable(Number expected, Number actual, double precision, double zeroThreshold){

		if(isZero(expected, zeroThreshold) && isZero(actual, zeroThreshold)){
			return true;
		}

		double zeroBoundary = expected.doubleValue() * (1d - precision); // Pointed towards zero
		double infinityBoundary = expected.doubleValue() * (1d + precision); // Pointed towards positive or negative infinity

		// Positive values
		if(expected.doubleValue() >= 0){
			return (actual.doubleValue() >= zeroBoundary) && (actual.doubleValue() <= infinityBoundary);
		} else

		// Negative values
		{
			return (actual.doubleValue() <= zeroBoundary) && (actual.doubleValue() >= infinityBoundary);
		}
	}