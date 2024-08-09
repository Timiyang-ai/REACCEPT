@Test
	public void onFlushDirty_shouldFailIfTheEditedObjectIsVoidedOrRetiredAndIgnoreIsSetToFalse() throws Exception {
		String[] propertyNames = new String[] { SomeImmutableEntityInterceptor.IMMUTABLE_FIELD_NAME };
		String[] previousState = new String[] { "old" };
		String[] currentState = new String[] { "new" };
		ImmutableEntityInterceptor interceptor = new SomeImmutableEntityInterceptor();
		expectedException.expect(APIException.class);
		expectedException.expectMessage(is(Context.getMessageSourceService().getMessage("editing.fields.not.allowed", new Object[] { "[immutable]", Order.class.getSimpleName() },  null)));
		Order order = new Order();
		order.setVoided(true);
		interceptor.onFlushDirty(order, null, currentState, previousState, propertyNames, null);
	}