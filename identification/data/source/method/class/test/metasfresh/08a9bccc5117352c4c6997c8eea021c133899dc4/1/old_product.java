public Quantity switchToSource()
	{
		return new Quantity(getSourceQty(), getSourceUOM(), getQty(), getUOM());
	}