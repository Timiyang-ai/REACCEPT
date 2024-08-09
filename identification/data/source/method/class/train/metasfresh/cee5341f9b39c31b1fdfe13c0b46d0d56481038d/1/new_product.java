public Quantity computeAssignableQuantity(@NonNull final RefundConfig refundCondig)
	{
		final Optional<RefundConfig> nextRefundConfig = getRefundContract()
				.getRefundConfigs()
				.stream()
				.filter(config -> config.getMinQty().compareTo(refundCondig.getMinQty()) > 0)
				.min(Comparator.comparing(RefundConfig::getMinQty));

		final I_C_UOM uomRecord = assignedQuantity.getUOM();

		if (!nextRefundConfig.isPresent())
		{
			return Quantity.infinite(uomRecord);
		}

		return Quantity
				.of(
						nextRefundConfig.get().getMinQty(),
						uomRecord)
				.subtract(getAssignedQuantity())
				.subtract(ONE)
				.max(Quantity.zero(uomRecord));
	}