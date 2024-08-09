@Test
    public void testReconcile_withResource_withKafka_noPrivate_configsReconcilable(TestContext context) {
        Topic kubeTopic = new Topic.Builder(topicName.toString(), 10, (short) 2, map("cleanup.policy", "bar"), metadata).build();
        Topic kafkaTopic = new Topic.Builder(topicName.toString(), 10, (short) 2, map("unclean.leader.election.enable", "true"), metadata).build();
        Topic privateTopic = null;
        Topic mergedTopic = new Topic.Builder(topicName.toString(), 10, (short) 2, map("unclean.leader.election.enable", "true", "cleanup.policy", "bar"), metadata).build();

        Async async0 = context.async(2);
        mockKafka.setCreateTopicResponse(topicName -> Future.succeededFuture());
        mockKafka.createTopic(kafkaTopic, ar -> async0.countDown());
        mockKafka.setUpdateTopicResponse(topicName -> Future.succeededFuture());

        KafkaTopic topic = TopicSerialization.toTopicResource(kubeTopic, resourcePredicate);
        mockK8s.setCreateResponse(topicName.asMapName(), null);
        mockK8s.createResource(topic, ar -> async0.countDown());
        mockK8s.setModifyResponse(topicName.asMapName(), null);
        mockTopicStore.setCreateTopicResponse(topicName, null);
        async0.await();

        Async async = context.async(2);
        topicOperator.reconcile(topic, kubeTopic, kafkaTopic, privateTopic, reconcileResult -> {
            assertSucceeded(context, reconcileResult);
            mockTopicStore.assertExists(context, topicName);
            mockK8s.assertExists(context, topicName.asMapName());
            mockKafka.assertExists(context, topicName);
            mockTopicStore.read(topicName, readResult -> {
                assertSucceeded(context, readResult);
                context.assertEquals(mergedTopic, readResult.result());
                async.countDown();
            });
            mockK8s.getFromName(topicName.asMapName(), readResult -> {
                assertSucceeded(context, readResult);
                context.assertEquals(mergedTopic, TopicSerialization.fromTopicResource(readResult.result()));
                async.countDown();
            });
            context.assertEquals(mergedTopic, mockKafka.getTopicState(topicName));
        });
    }