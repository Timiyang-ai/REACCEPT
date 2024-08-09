	@Test
	public void onFlushDirty_shouldReturnFalseForNonAuditableObjects() {
		AuditableInterceptor interceptor = new AuditableInterceptor();
		
		Object o = new Object();
		
		boolean returnValue = interceptor.onFlushDirty(o, null, null, null, null, null);
		
		Assert.assertFalse("false should have been returned because we didn't pass in an Auditable or OpenmrsObject",
		    returnValue);
	}