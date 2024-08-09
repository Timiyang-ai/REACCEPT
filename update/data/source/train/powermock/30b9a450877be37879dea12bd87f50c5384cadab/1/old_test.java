@Test
	public void testGetNumber() throws Exception {
		suppress(constructor(SuppressConstructorHierarchyParent.class));
		SuppressConstructorHierarchyParent tested = new SuppressConstructorHierarchyParent("message");
		assertEquals(42, tested.getNumber());
	}