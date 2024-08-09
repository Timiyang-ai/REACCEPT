@Test
    public void setBaselineTopology() throws Exception {
        cluster.baselineAutoAdjustEnabled(false);

        IgniteEx ignite_2 = startGrid(2);
        IgniteEx ignite_3 = startGrid(3);

        List<String> ids = Lists.newArrayList(
            cluster.localNode().id().toString(),
            ignite_2.cluster().localNode().id().toString(),
            ignite_3.cluster().localNode().id().toString()
        );

        Request req = new Request()
            .setId(UUID.randomUUID())
            .setAction("BaselineActions.setBaselineTopology")
            .setArgument(ids);

        executeAction(req, (r) -> r.getStatus() == COMPLETED && cluster.currentBaselineTopology().size() == 3);
    }