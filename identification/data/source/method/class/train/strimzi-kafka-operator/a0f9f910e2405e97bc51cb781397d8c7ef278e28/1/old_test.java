@Test
    public void testReconcile_noResource_withKafka_noPrivate(TestContext context) {

        Topic kubeTopic = null;
        Topic kafkaTopic = new Topic.Builder(topicName.toString(), 10, (short) 2, map("cleanup.policy", "bar"), metadata).build();
        Topic privateTopic = null;

        Async async0 = context.async();
        mockTopicStore.setCreateTopicResponse(topicName, null);
        mockK8s.setCreateResponse(topicName.asKubeName(), null);
        mockKafka.setCreateTopicResponse(topicName -> Future.succeededFuture());
        mockKafka.createTopic(kafkaTopic).setHandler(ar -> async0.complete());
        async0.await();
        LogContext logContext = LogContext.periodic(topicName.toString());
        Async async = context.async(2);
        topicOperator.reconcile(logContext, null, kubeTopic, kafkaTopic, privateTopic, reconcileResult -> {
            assertSucceeded(context, reconcileResult);
            mockTopicStore.assertExists(context, topicName);
            mockK8s.assertExists(context, topicName.asKubeName());
            mockKafka.assertExists(context, topicName);
            mockK8s.assertNoEvents(context);
            mockTopicStore.read(topicName).setHandler(readResult -> {
                assertSucceeded(context, readResult);
                context.assertEquals(kafkaTopic, readResult.result());
                async.countDown();
            });
            mockK8s.getFromName(topicName.asKubeName()).setHandler(readResult -> {
                assertSucceeded(context, readResult);
                context.assertEquals(kafkaTopic, TopicSerialization.fromTopicResource(readResult.result()));
                async.countDown();
            });
            context.assertEquals(kafkaTopic, mockKafka.getTopicState(topicName));
        });
    }