    @Test
    public void register_Invocation() {
        Operation op = new DummyBackupAwareOperation();
        Invocation invocation = newInvocation(op);
        long oldCallId = invocationRegistry.getLastCallId();

        invocationRegistry.register(invocation);

        assertEquals(oldCallId + 1, op.getCallId());
        assertSame(invocation, invocationRegistry.get(op.getCallId()));
    }