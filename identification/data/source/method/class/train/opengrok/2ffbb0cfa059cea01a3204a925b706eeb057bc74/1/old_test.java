    @Test
    public void isChattyStatusPage() {
        RuntimeEnvironment env = RuntimeEnvironment.getInstance();

        // By default, status page should not be chatty.
        assertFalse(env.isChattyStatusPage());

        env.setChattyStatusPage(true);
        assertTrue(env.isChattyStatusPage());

        env.setChattyStatusPage(false);
        assertFalse(env.isChattyStatusPage());
    }