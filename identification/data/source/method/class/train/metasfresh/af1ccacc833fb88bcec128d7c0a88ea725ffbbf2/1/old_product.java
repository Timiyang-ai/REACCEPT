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
					.setScale(precision + 2)
					.divide(ONE_HUNDRED_VALUE, RoundingMode.UNNECESSARY)
					.multiply(valueAsBigDecimal)
					.setScale(precision, RoundingMode.HALF_UP);
		}
	}