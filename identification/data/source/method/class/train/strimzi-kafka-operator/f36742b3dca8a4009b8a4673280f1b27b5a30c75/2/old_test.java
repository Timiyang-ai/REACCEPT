@Test
    public void testReconcile_noResource_withKafka_noPrivate(TestContext context) {

        Topic kubeTopic = null;
        Topic kafkaTopic = new Topic.Builder(topicName.toString(), 10, (short) 2, map("cleanup.policy", "bar"), metadata).build();
        Topic privateTopic = null;

        Async async0 = context.async();
        mockTopicStore.setCreateTopicResponse(topicName, null);
        mockK8s.setCreateResponse(topicName.asKubeName(), null);
        mockKafka.setCreateTopicResponse(topicName -> Future.succeededFuture());
        mockKafka.createTopic(kafkaTopic, ar -> async0.complete());
        async0.await();

        Async async = context.async(2);
        topicOperator.reconcile(null, kubeTopic, kafkaTopic, privateTopic, reconcileResult -> {
            assertSucceeded(context, reconcileResult);
            mockTopicStore.assertExists(context, topicName);
            mockK8s.assertExists(context, topicName.asKubeName());
            mockKafka.assertExists(context, topicName);
            mockK8s.assertNoEvents(context);
            mockTopicStore.read(topicName, readResult -> {
                assertSucceeded(context, readResult);
                context.assertEquals(kafkaTopic, readResult.result());
                async.countDown();
            });
            mockK8s.getFromName(topicName.asKubeName(), readResult -> {
                assertSucceeded(context, readResult);
                context.assertEquals(kafkaTopic, TopicSerialization.fromTopicResource(readResult.result()));
                async.countDown();
            });
            context.assertEquals(kafkaTopic, mockKafka.getTopicState(topicName));
        });
    }