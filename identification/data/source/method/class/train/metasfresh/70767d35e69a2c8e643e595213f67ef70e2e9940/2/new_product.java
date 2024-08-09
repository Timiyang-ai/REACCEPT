private void setQty(final I_EDI_DesadvLine desadvLine, final BigDecimal newMovementQty)
	{
		final IUOMConversionBL uomConversionBL = Services.get(IUOMConversionBL.class);

		final Properties ctx = InterfaceWrapperHelper.getCtx(desadvLine);
		final ProductId productId = ProductId.ofRepoId(desadvLine.getM_Product_ID());

		desadvLine.setMovementQty(newMovementQty);
		desadvLine.setQtyDeliveredInUOM(uomConversionBL.convertFromProductUOM(ctx, productId, desadvLine.getC_UOM(), newMovementQty));
	}