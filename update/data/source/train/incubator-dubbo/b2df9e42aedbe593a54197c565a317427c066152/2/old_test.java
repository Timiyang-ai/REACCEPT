@Test
    public void testRegister() {
        Set<String> registered = null;
        // clear first
        registered = registry.getRegistered();

        for (int i = 0; i < 2; i++) {
            registry.register(serviceUrl, null);
            registered = registry.getRegistered();
            assertTrue(registered.contains(serviceUrl.toFullString()));
        }
        // confirm only 1 regist success;
        registered = registry.getRegistered();
        assertEquals(1, registered.size());
    }