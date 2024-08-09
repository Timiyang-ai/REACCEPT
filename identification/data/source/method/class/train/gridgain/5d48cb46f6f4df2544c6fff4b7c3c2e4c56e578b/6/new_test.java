@Test
    public void testDeactivate() throws Exception {
        Ignite ignite = startGrids(1);

        assertFalse(ignite.cluster().active());
        assertEquals(INACTIVE, ignite.cluster().state());

        ignite.cluster().state(ACTIVE);

        assertTrue(ignite.cluster().active());
        assertEquals(ACTIVE, ignite.cluster().state());

        injectTestSystemOut();

        assertEquals(EXIT_CODE_OK, execute("--deactivate"));

        assertFalse(ignite.cluster().active());
        assertEquals(INACTIVE, ignite.cluster().state());

        assertContains(log, testOut.toString(), "Command deprecated. Use --set-state instead.");
    }