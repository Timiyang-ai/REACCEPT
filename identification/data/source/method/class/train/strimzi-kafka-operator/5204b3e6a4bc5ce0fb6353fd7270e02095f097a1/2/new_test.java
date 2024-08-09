@Test
    public void testReconcile_noResource_withKafka_withPrivate(TestContext context) {
        Topic kubeTopic = null;
        Topic kafkaTopic = new Topic.Builder(topicName.toString(), 10, (short) 2, map("cleanup.policy", "bar")).build();
        Topic privateTopic = kafkaTopic;

        Async async0 = context.async(2);
        mockKafka.createTopic(kafkaTopic, ar -> async0.countDown());
        mockKafka.setDeleteTopicResponse(topicName, null);
        mockTopicStore.setCreateTopicResponse(topicName, null);
        mockTopicStore.create(kafkaTopic, ar -> async0.countDown());
        mockTopicStore.setDeleteTopicResponse(topicName, null);
        async0.await();

        Async async = context.async();
        topicOperator.reconcile(null, kubeTopic, kafkaTopic, privateTopic, reconcileResult -> {
            assertSucceeded(context, reconcileResult);
            mockTopicStore.assertNotExists(context, topicName);
            mockK8s.assertNotExists(context, topicName.asMapName());
            mockKafka.assertNotExists(context, topicName);
            mockK8s.assertNoEvents(context);
            async.complete();
        });
    }