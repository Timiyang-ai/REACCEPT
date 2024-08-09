	@Test
	public void test_zero_static_factory()
	{
		final I_C_UOM uom = uomHelper.createUOM("UOM1", 2);
		final Quantity qty = Quantity.zero(uom);
		assertThat(qty.isZero()).isTrue();
	}