@Test
	public void testGetNumber() throws Exception {
		suppress(constructor(SuppressConstructorHierarchy.class));
		SuppressConstructorHierarchy tested = new SuppressConstructorHierarchy("message");
		assertEquals(42, tested.getNumber());
	}