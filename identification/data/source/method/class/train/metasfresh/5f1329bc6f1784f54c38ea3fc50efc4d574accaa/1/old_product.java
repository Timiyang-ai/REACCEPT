public static final Quantity zero(final I_C_UOM uom)
	{
		return new Quantity(BigDecimal.ZERO, uom, BigDecimal.ZERO, uom);
	}