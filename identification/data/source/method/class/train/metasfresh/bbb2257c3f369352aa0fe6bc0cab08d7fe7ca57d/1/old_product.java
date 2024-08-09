public BigDecimal subtractFromBase(@NonNull final BigDecimal base, final int precision)
	{
		Check.assumeGreaterOrEqualToZero(precision, "precision");

		if (base.signum() == 0)
		{
			return BigDecimal.ZERO;
		}
		else if (isZero())
		{
			return base.setScale(precision, RoundingMode.HALF_UP);
		}
		else if (isOneHundred())
		{
			return BigDecimal.ZERO;
		}
		else
		{
			return base
					.setScale(precision + 2)
					.divide(ONE_HUNDRED_VALUE, RoundingMode.UNNECESSARY) // no rounding needed because we raised the current precision by 2
					.multiply(ONE_HUNDRED_VALUE.subtract(valueAsBigDecimal))
					.setScale(precision, RoundingMode.HALF_UP);
		}
	}