@Test
	public void onFlushDirty_shouldFailIfAnEntityHasAChangedProperty() throws Exception {
		String[] propertyNames = new String[] { SomeImmutableEntityInterceptor.IMMUTABLE_FIELD_NAME };
		String[] previousState = new String[] { "old" };
		String[] currentState = new String[] { "new" };
		ImmutableEntityInterceptor interceptor = new SomeImmutableEntityInterceptor();
		expectedException.expect(APIException.class);
		expectedException.expectMessage(is("Editing some fields on " + interceptor.getSupportedType().getSimpleName()
		        + " is not allowed"));
		interceptor.onFlushDirty(new Order(), null, currentState, previousState, propertyNames, null);
	}