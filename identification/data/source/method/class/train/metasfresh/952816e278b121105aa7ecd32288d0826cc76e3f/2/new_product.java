public Percent roundToHalf(@NonNull final RoundingMode roundingMode)
	{
		final BigDecimal newPercentValue = toBigDecimal()
				.multiply(TWO_VALUE)
				.setScale(0, roundingMode)
				.divide(TWO_VALUE)
				.setScale(1, roundingMode); // AFAIU not needed, but who knows..

		return Percent.of(newPercentValue);
	}