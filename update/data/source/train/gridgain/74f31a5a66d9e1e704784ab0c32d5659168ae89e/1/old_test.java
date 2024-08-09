@Test
    public void testSetState() throws Exception {
        Ignite ignite = startGrids(2);

        assertEquals(INACTIVE, ignite.cluster().state());

        // INACTIVE -> INACTIVE.
        setState(ignite, INACTIVE, "INACTIVE");

        // INACTIVE -> READ_ONLY.
        setState(ignite, READ_ONLY, "READ_ONLY");

        // READ_ONLY -> READ_ONLY.
        setState(ignite, READ_ONLY, "READ_ONLY");

        // READ_ONLY -> ACTIVE.
        setState(ignite, ACTIVE, "ACTIVE");

        // ACTIVE -> ACTIVE.
        setState(ignite, ACTIVE, "ACTIVE");

        // ACTIVE -> INACTIVE.
        setState(ignite, INACTIVE, "INACTIVE");

        // INACTIVE -> ACTIVE.
        setState(ignite, ACTIVE, "ACTIVE");

        // ACTIVE -> READ_ONLY.
        setState(ignite, READ_ONLY, "READ_ONLY");

        // READ_ONLY -> INACTIVE.
        setState(ignite, INACTIVE, "INACTIVE");
    }