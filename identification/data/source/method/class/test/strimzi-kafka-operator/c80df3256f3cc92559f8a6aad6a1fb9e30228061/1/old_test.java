@Test
    public void changeReplicationFactor_TransientErrorInVerify(TestContext context) {
        MockAdminClient adminClient = new MockAdminClient();
        Vertx vertx = Vertx.vertx();
        Topic topic = new Topic.Builder("changeReplicationFactor", 2, (short) 2, emptyMap()).build();
        String[] partitions = new String[]{"changeReplicationFactor-0", "changeReplicationFactor-1"};
        Subclass sub = new Subclass(adminClient, vertx, config, asList(
                Subclass.generate("{\"version\":1,\"partitions\":[{\"topic\":\"test-topic\",\"partition\":0,\"replicas\":[0],\"log_dirs\":[\"any\"]},{\"topic\":\"test-topic\",\"partition\":1,\"replicas\":[0],\"log_dirs\":[\"any\"]}]}",
                        "{\"version\":1,\"partitions\":[{\"topic\":\"test-topic\",\"partition\":0,\"replicas\":[0],\"log_dirs\":[\"any\"]},{\"topic\":\"test-topic\",\"partition\":1,\"replicas\":[0],\"log_dirs\":[\"any\"]}]}"),
                Subclass.executeStarted(),
                Subclass.verifyInProgress(partitions),
                Subclass.fail("Bang!"),
                Subclass.verifySuccess(partitions)));
        Async async = context.async();
        sub.changeReplicationFactor(topic, ar -> {
            // We should retry anf ultimately succeed
            context.assertTrue(ar.succeeded());
            async.complete();
        });
    }