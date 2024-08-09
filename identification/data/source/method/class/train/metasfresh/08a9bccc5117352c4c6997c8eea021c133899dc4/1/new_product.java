public Quantity switchToSource()
	{
		return new Quantity(getSourceQty(), getSourceUOM(), toBigDecimal(), getUOM());
	}