    @Test
    public void deregister_whenSkipped() {
        Operation op = new DummyOperation();
        Invocation invocation = newInvocation(op);

        invocationRegistry.register(invocation);
        invocationRegistry.deregister(invocation);

        assertFalse(invocation.isActive());
    }