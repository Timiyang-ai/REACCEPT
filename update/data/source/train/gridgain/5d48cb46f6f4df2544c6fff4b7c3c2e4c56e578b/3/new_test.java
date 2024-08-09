@Test
    public void testActivate() throws Exception {
        Ignite ignite = startGrids(1);

        injectTestSystemOut();

        assertEquals(INACTIVE, ignite.cluster().state());

        assertEquals(EXIT_CODE_OK, execute("--activate"));

        assertEquals(ACTIVE, ignite.cluster().state());

        assertContains(log, testOut.toString(), "Command deprecated. Use --set-state instead.");
    }