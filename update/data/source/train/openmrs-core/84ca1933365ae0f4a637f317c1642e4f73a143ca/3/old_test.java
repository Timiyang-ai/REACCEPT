@Test
	public void onFlushDirty_shouldReturnFalseForNonAuditableObjects() throws Exception {
		AuditableInterceptor interceptor = new AuditableInterceptor();
		
		GlobalProperty o = new GlobalProperty();
		
		// sanity check to make sure we're using the right object for this test
		Assert.assertTrue(o instanceof OpenmrsObject);
		Assert.assertFalse(o instanceof Auditable);
		
		boolean returnValue = interceptor.onFlushDirty(o, null, null, null, null, null);
		
		Assert.assertFalse("false should have been returned because we didn't pass in an Auditable", returnValue);
	}