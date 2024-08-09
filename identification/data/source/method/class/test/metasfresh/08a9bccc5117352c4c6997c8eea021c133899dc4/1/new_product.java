public Quantity switchToSource()
	{
		return new Quantity(getSourceQty(), getSourceUOM(), getAsBigDecimal(), getUOM());
	}