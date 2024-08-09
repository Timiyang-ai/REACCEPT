@Test
	public void testInitEncoder() {
		setUp();
		initASE();
		ase.initEncoder(3, 1, 8, 14, 1.5, 0.5);
		Assert.assertNotNull("AdaptiveScalarEncoder class is null", ase);
		
		/////////// Negative Test ///////////
		setUp();
		initASE();
        Assert.assertNotNull("AdaptiveScalarEncoder class is null", ase);
        try {
            ase.setPeriodic(true); // Should cause failure during init
            ase.initEncoder(3, 1, 8, 14, 1.5, 0.5);
            fail();
        }catch(Exception e) {
            assertEquals(IllegalStateException.class, e.getClass());
            assertEquals("Adaptive scalar encoder does not encode periodic inputs", e.getMessage());
        }
	}