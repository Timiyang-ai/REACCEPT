    @Test
    public void freeCapacityOf() {
        assertEquals(new NodeResources(5, 4, 8, 2, NodeResources.DiskSpeed.fast, NodeResources.StorageType.remote),
                     capacity.freeCapacityOf(host1, false));
        assertEquals(new NodeResources(5, 6, 8, 4.5, NodeResources.DiskSpeed.fast, NodeResources.StorageType.remote),
                     capacity.freeCapacityOf(host3, false));

        doAnswer(invocation -> {
            NodeResources totalHostResources = (NodeResources) invocation.getArguments()[0];
            return totalHostResources.subtract(new NodeResources(1, 2, 3, 0.5, NodeResources.DiskSpeed.any));
        }).when(hostResourcesCalculator).availableCapacityOf(any());

        assertEquals(new NodeResources(4, 2, 5, 1.5, NodeResources.DiskSpeed.fast, NodeResources.StorageType.remote),
                     capacity.freeCapacityOf(host1, false));
        assertEquals(new NodeResources(4, 4, 5, 4, NodeResources.DiskSpeed.fast, NodeResources.StorageType.remote),
                     capacity.freeCapacityOf(host3, false));
    }