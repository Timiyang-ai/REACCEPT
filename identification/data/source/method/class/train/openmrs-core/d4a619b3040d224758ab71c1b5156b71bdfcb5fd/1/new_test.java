	@Test
	public void onFlushDirty_shouldFailIfAnEntityHasAChangedProperty() {
		String[] propertyNames = new String[] { SomeImmutableEntityInterceptor.IMMUTABLE_FIELD_NAME };
		String[] previousState = new String[] { "old" };
		String[] currentState = new String[] { "new" };
		ImmutableEntityInterceptor interceptor = new SomeImmutableEntityInterceptor();
		expectedException.expect(UnchangeableObjectException.class);
		expectedException.expectMessage(is(Context.getMessageSourceService().getMessage("editing.fields.not.allowed",
		    new Object[] { "[immutable]", Order.class.getSimpleName() }, null)));
		interceptor.onFlushDirty(new Order(), null, currentState, previousState, propertyNames, null);
	}