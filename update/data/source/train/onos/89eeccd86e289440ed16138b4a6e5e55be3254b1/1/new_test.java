@Test
    public void testAvailableResources() {
        MetricValue mv = new MetricValue.Builder().load(50).add();

        Set<String> diskSet = ImmutableSet.of("disk1", "disk2");

        diskSet.forEach(disk -> DISK_METRICS.forEach(cmt ->
                testUpdateMetricWithResource(cmt, mv, disk)));

        Set<String> networkSet = ImmutableSet.of("eth0", "eth1");

        networkSet.forEach(network -> NETWORK_METRICS.forEach(cmt ->
                testUpdateMetricWithResource(cmt, mv, network)));

        assertThat(monitor.availableResourcesSync(nodeId, Type.DISK), is(diskSet));
        assertThat(monitor.availableResourcesSync(nodeId, Type.NETWORK), is(networkSet));
    }