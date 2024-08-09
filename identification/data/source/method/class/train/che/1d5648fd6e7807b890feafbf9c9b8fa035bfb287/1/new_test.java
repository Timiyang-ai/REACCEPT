@Ignore
    @Test
    public void getMacros() throws Exception {
        final Set<Macro> macros = provider.getMacros(machine);

        assertEquals(macros.size(), 2);

        final Iterator<Macro> iterator = macros.iterator();

        final Macro provider1 = iterator.next();

        assertTrue(provider1 instanceof BaseMacro);
        assertEquals(provider1.getName(), ServerHostNameMacro.KEY.replace("%", WS_AGENT_PORT));

        provider1.expand().then(new Operation<String>() {
            @Override
            public void apply(String address) throws OperationException {
                assertEquals(address, PROTOCOL);
            }
        });

        final Macro provider2 = iterator.next();

        assertTrue(provider2 instanceof BaseMacro);
        assertEquals(provider2.getName(), ServerHostNameMacro.KEY.replace("%", WS_AGENT_PORT.substring(0, WS_AGENT_PORT.length() - 4)));

        provider2.expand().then(new Operation<String>() {
            @Override
            public void apply(String address) throws OperationException {
                assertEquals(address, PROTOCOL);
            }
        });
    }