@Test
    public void testReconcile_withCm_noKafka_withPrivate(TestContext context) {

        Topic kubeTopic = new Topic.Builder(topicName.toString(), 10, (short)2, map("cleanup.policy", "bar")).build();
        Topic kafkaTopic = null;
        Topic privateTopic = kubeTopic;

        Async async0 = context.async(2);
        mockK8s.setCreateResponse(mapName, null)
                .createConfigMap(TopicSerialization.toConfigMap(kubeTopic, cmPredicate), ar -> async0.countDown());
        mockK8s.setDeleteResponse(topicName, null);
        mockTopicStore.setCreateTopicResponse(topicName, null)
                .create(privateTopic, ar-> async0.countDown());
        mockTopicStore.setDeleteTopicResponse(topicName, null);

        Async async = context.async();

        controller.reconcile(null, kubeTopic, kafkaTopic, privateTopic, reconcileResult -> {
            assertSucceeded(context, reconcileResult);
            mockKafka.assertNotExists(context, kubeTopic.getTopicName());
            mockTopicStore.assertNotExists(context, kubeTopic.getTopicName());
            mockK8s.assertNotExists(context, kubeTopic.getMapName());
            mockK8s.assertNoEvents(context);
            async.complete();
        });
    }