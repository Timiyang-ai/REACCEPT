private void setQty(final I_EDI_DesadvLine desadvLine, final BigDecimal newMovementQty)
	{
		final IUOMConversionBL uomConversionBL = Services.get(IUOMConversionBL.class);

		final Properties ctx = InterfaceWrapperHelper.getCtx(desadvLine);

		desadvLine.setMovementQty(newMovementQty);
		desadvLine.setQtyDeliveredInUOM(uomConversionBL.convertFromProductUOM(ctx, desadvLine.getM_Product(), desadvLine.getC_UOM(), newMovementQty));
	}