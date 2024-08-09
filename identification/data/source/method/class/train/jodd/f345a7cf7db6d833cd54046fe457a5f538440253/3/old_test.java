	@Test
	void isSameTypeAsResourceTest() {
		final JtxTransactionManager manager = createManager();
		final JtxTransaction jtx = manager.requestTransaction(new JtxTransactionMode(JtxPropagationBehavior.PROPAGATION_REQUIRED, true));

		final FooRes foo = jtx.requestResource(FooRes.class);
		assertNotNull(foo);
		assertTrue(foo instanceof Foo);
		assertEquals("Foo", foo.value());
	}