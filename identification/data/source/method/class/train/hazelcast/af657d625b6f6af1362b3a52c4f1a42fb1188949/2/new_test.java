    @Test
    public void changeClusterState() throws Exception {
        assertTrueEventually(
                () -> assertEquals(ACTIVE, hazelcastInstances[0].getCluster().getClusterState()));
        waitClusterForSafeState(hazelcastInstances[0]);

        resolve(managementCenterService.changeClusterState(PASSIVE));

        assertClusterState(PASSIVE, hazelcastInstances);
    }