	@Test
	public void test_switchToSource()
	{
		final BigDecimal qty = new BigDecimal("1234");
		final I_C_UOM uom = uomHelper.createUOM("UOM1", 2);

		final BigDecimal sourceQty = new BigDecimal("1235");
		final I_C_UOM sourceUOM = uomHelper.createUOM("UOM2", 2);

		final Quantity quantity = new Quantity(qty, uom, sourceQty, sourceUOM);
		Assert.assertSame("Invalid Qty", qty, quantity.toBigDecimal());
		Assert.assertSame("Invalid UOM", uom, quantity.getUOM());
		Assert.assertSame("Invalid Source Qty", sourceQty, quantity.getSourceQty());
		Assert.assertSame("Invalid Source UOM", sourceUOM, quantity.getSourceUOM());

		final Quantity quantitySource = quantity.switchToSource();
		new QuantityExpectation()
				.sameQty(sourceQty)
				.uom(sourceUOM)
				.sameSourceQty(qty)
				.sourceUOM(uom)
				.assertExpected(quantitySource);
	}