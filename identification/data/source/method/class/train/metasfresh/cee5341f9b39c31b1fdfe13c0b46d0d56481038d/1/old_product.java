public Quantity computeAssignableQuantity()
	{
		if(RefundMode.ALL_MAX_SCALE.equals(refundConfig.getRefundMode()))
		{
			return Quantity.infinite(assignedQuantity.getUOM());
		}

		final Optional<RefundConfig> nextRefundConfig = getRefundContract()
				.getRefundConfigs()
				.stream()
				.filter(config -> config.getMinQty().compareTo(getRefundConfig().getMinQty()) > 0)
				.min(Comparator.comparing(RefundConfig::getMinQty));

		if (!nextRefundConfig.isPresent())
		{
			return Quantity.infinite(assignedQuantity.getUOM());
		}

		return Quantity
				.of(
						nextRefundConfig.get().getMinQty(),
						assignedQuantity.getUOM())
				.subtract(getAssignedQuantity())
				.subtract(ONE);
	}