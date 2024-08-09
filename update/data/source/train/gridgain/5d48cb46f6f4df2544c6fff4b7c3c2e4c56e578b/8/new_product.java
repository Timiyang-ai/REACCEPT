private void activateSimple(int srvs, int clients, int changeFrom, ClusterState state) throws Exception {
        assertActive(state);

        changeStateSimple(srvs, clients, changeFrom, INACTIVE, state);
    }