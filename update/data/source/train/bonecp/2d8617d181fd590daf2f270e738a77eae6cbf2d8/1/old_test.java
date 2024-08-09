@Test
	public void testMarkPossiblyBroken() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Field field = this.testClass.getClass().getDeclaredField("possiblyBroken");
		field.setAccessible(true);
		field.set(this.testClass, false);
		this.testClass.markPossiblyBroken(new SQLException());
		Assert.assertTrue(field.getBoolean(this.testClass));

		// Test that a db fatal error will lead to the pool being instructed to terminate all connections (+ log)
		this.mockPool.terminateAllConnections();
		this.mockLogger.error((String)anyObject(), anyObject());
		replay(this.mockPool);
		this.testClass.markPossiblyBroken(new SQLException("test", "08001"));
		verify(this.mockPool);


	}