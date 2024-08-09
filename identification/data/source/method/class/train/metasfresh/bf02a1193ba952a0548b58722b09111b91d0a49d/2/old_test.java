	@Test
	public void test_add_ZeroQty_TotallyDifferentUOMs()
	{
		final I_C_UOM qty_uom = uomHelper.createUOM("qty_uom", 2);
		final I_C_UOM qty_sourceUom = uomHelper.createUOM("qty_sourceUom", 2);
		final I_C_UOM qtyToAdd_uom = uomHelper.createUOM("qtyToAdd_uom", 2);
		final I_C_UOM qtyToAdd_sourceUom = uomHelper.createUOM("qtyToAdd_sourceUom", 2);

		final Quantity qty = new Quantity(new BigDecimal("123"), qty_uom, new BigDecimal("456"), qty_sourceUom);
		final Quantity qtyToAdd = new Quantity(new BigDecimal("0"), qtyToAdd_uom, new BigDecimal("0"), qtyToAdd_sourceUom);
		final Quantity qtyNew = qty.add(qtyToAdd);
		Assert.assertSame("Invalid QtyNew", qty, qtyNew);
	}