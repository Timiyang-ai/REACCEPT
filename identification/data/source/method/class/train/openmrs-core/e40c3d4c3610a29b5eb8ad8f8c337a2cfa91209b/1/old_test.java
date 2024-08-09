	@Test
	public void onSave_shouldReturnTrueIfDateCreatedWasNull() {
		AuditableInterceptor interceptor = new AuditableInterceptor();
		
		User u = new User();
		
		String[] propertyNames = new String[] { "creator", "dateCreated" };
		Object[] currentState = new Object[] { 0, null };
		
		boolean result = interceptor.onSave(u, 0, currentState, propertyNames, null);
		Assert.assertTrue(result);
	}