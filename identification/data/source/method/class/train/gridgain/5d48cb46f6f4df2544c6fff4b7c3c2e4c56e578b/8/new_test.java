@Test
    public void testState() throws Exception {
        final String newTag = "new_tag";

        Ignite ignite = startGrids(1);

        injectTestSystemOut();

        assertFalse(ignite.cluster().active());

        injectTestSystemOut();

        assertEquals(EXIT_CODE_OK, execute("--state"));

        String out = testOut.toString();

        UUID clId = ignite.cluster().id();
        String clTag = ignite.cluster().tag();

        assertContains(log, out, "Cluster is inactive");
        assertContains(log, out, "Cluster  ID: " + clId);
        assertContains(log, out, "Cluster tag: " + clTag);

        ignite.cluster().active(true);

        assertTrue(ignite.cluster().active());

        assertEquals(EXIT_CODE_OK, execute("--state"));

        assertContains(log, testOut.toString(), "Cluster is active");

        boolean tagUpdated = GridTestUtils.waitForCondition(() -> {
            try {
                ignite.cluster().tag(newTag);
            }
            catch (IgniteCheckedException e) {
                return false;
            }

            return true;
        }, 10_000);

        assertTrue("Tag has not been updated in 10 seconds.", tagUpdated);

        assertEquals(EXIT_CODE_OK, execute("--state"));

        assertContains(log, testOut.toString(), "Cluster tag: " + newTag);

        ignite.cluster().state(ACTIVE_READ_ONLY);

        awaitPartitionMapExchange();

        assertEquals(ACTIVE_READ_ONLY, ignite.cluster().state());

        assertEquals(EXIT_CODE_OK, execute("--state"));

        assertContains(log, testOut.toString(), "Cluster is active (read-only)");
    }