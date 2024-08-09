@Test
	public void onFlushDirty_shouldSetTheChangedByField() throws Exception {
		AuditableInterceptor interceptor = new AuditableInterceptor();
		
		User u = new User();
		
		// sanity check
		Assert.assertTrue(u instanceof Auditable);
		
		String[] propertyNames = new String[] { "changedBy", "dateChanged" };
		Object[] currentState = new Object[] { "", null };
		
		interceptor.onFlushDirty(u, null, currentState, null, propertyNames, null);
		
		Assert.assertNotNull(currentState[0]);
	}