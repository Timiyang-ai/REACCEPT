private void setState(Ignite ignite, ClusterState state, String strState) {
        assertEquals(EXIT_CODE_OK, execute("--set-state", strState));

        assertEquals(state, ignite.cluster().state());
    }