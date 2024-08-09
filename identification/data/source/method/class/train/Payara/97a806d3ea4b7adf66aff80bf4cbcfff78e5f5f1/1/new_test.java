    @Test
    public void getParamValueTest() {
        DummyAdminCommand dac = new DummyAdminCommand();
        dac.foo = "test";
        WrappedAdminCommand wrappedCommand = new WrappedAdminCommand(dac) {
            @Override
            public void execute(AdminCommandContext context) {
                // nothing todo
            }
        };
        assertEquals("set param value", "test", CommandSupport.getParamValue(wrappedCommand, "foo"));
        // note, after resolver it must be not null
        assertNull("unset param value must be null", CommandSupport.getParamValue(wrappedCommand, "foobar"));
        assertNull("non existent param value must be null", CommandSupport.getParamValue(wrappedCommand, "dummy"));
    }