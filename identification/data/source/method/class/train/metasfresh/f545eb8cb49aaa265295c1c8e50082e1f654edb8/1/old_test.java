	@Test
	public void test_infinite_static_factory()
	{
		final I_C_UOM uom = uomHelper.createUOM("UOM1", 2);
		final Quantity qty = Quantity.infinite(uom);
		assertThat(qty.isInfinite()).isTrue();
	}