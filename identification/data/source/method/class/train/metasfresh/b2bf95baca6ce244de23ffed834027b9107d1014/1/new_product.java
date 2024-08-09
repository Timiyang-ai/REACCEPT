public Quantity weightedAverage(@NonNull final BigDecimal previousAverage, final int previousAverageWeight)
	{
		Check.assume(previousAverageWeight >= 0, "previousAverageWeight >= 0");

		final BigDecimal previousAverageWeightBD = BigDecimal.valueOf(previousAverageWeight);
		final BigDecimal count = BigDecimal.valueOf(previousAverageWeight + 1);
		final int precision = getUOM().getStdPrecision();

		final BigDecimal currentQty = getQty();
		final BigDecimal currentWeightAverage = previousAverage
				.multiply(previousAverageWeightBD)
				.add(currentQty)
				.divide(count, precision, RoundingMode.HALF_UP);

		return new Quantity(currentWeightAverage, getUOM());
	}