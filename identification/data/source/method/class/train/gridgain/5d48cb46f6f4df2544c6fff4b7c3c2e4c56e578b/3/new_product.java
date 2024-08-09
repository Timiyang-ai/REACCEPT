private void deactivateSimple(int srvs, int clients, int changeFrom, ClusterState initialState) throws Exception {
        assertActive(initialState);

        changeStateSimple(srvs, clients, changeFrom, initialState, INACTIVE);
    }