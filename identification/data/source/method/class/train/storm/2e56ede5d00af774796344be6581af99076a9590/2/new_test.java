    @Test
    public void getAssignedMemoryForSlot_allNull() {
        Map<String, Object> topConf = getEmptyConfig();
        Assert.assertEquals(TOPOLOGY_WORKER_DEFAULT_MEMORY_ALLOCATION, Cluster.getAssignedMemoryForSlot(topConf), 0);
    }