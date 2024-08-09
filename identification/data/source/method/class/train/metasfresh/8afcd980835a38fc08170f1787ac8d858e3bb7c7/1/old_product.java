public static Object stripZerosAndLogIssueIfBigDecimalScaleTooBig(
			@Nullable final Object value,
			@NonNull PO po)
	{
		final boolean valueIsNotBigDecimal = !(value instanceof BigDecimal);
		if (valueIsNotBigDecimal)
		{
			return value; // nothing to do
		}

		final int maxAllowedScale = 15;
		final BigDecimal bdValue = (BigDecimal)value;
		if (bdValue.scale() <= maxAllowedScale)
		{
			return bdValue; // nothing to do
		}

		final BigDecimal bpWithoutTrailingZeroes = NumberUtils.stripTrailingDecimalZeros(bdValue);

		final String firstMessagePart = StringUtils.formatMessage(
				"The given value has scale={}; going to proceed with a stripped down value with scale={};",
				bdValue.scale(), bpWithoutTrailingZeroes.scale());
		final String lastMessagePart = StringUtils.formatMessage(" value={}; po={}", bdValue, po);

		final I_AD_Issue issue = Services.get(IErrorManager.class).createIssue(new AdempiereException(firstMessagePart + lastMessagePart));
		logger.warn(firstMessagePart + " created AD_Issue_ID={}" + lastMessagePart, issue.getAD_Issue_ID());

		return bpWithoutTrailingZeroes;
	}