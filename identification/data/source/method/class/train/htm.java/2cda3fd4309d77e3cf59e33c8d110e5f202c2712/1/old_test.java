@Test
	public void testInitEncoder() {
		setUp();
		initASE();
		ase.initEncoder(3, 1, 8, 14, 1.5, 0.5);
		Assert.assertNotNull("AdaptiveScalarEncoder class is null", ase);
	}