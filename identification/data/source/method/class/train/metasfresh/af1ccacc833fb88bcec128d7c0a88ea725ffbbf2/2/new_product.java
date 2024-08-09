public BigDecimal multiply(@NonNull final BigDecimal base, final int precision)
	{
		Check.assumeGreaterOrEqualToZero(precision, "precision");

		if (base.signum() == 0)
		{
			return BigDecimal.ZERO;
		}
		else if (isZero())
		{
			return BigDecimal.ZERO;
		}
		else if (isOneHundred())
		{
			return base.setScale(precision, RoundingMode.HALF_UP);
		}
		else
		{
			return base
					.setScale(precision + 2, RoundingMode.HALF_UP)
					.divide(ONE_HUNDRED_VALUE, RoundingMode.HALF_UP)
					.multiply(value)
					.setScale(precision, RoundingMode.HALF_UP);
		}
	}