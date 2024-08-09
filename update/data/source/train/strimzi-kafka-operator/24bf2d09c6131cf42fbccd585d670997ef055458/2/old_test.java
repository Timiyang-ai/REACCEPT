@Test
    public void testReconcile_withCm_noKafka_withPrivate(TestContext context) {

        Topic kubeTopic = new Topic.Builder(topicName.toString(), 10, (short)2, map("foo", "bar")).build();
        Topic kafkaTopic = null;
        Topic privateTopic = kubeTopic;

        mockK8s.setCreateResponse(mapName, null)
                .createConfigMap(TopicSerialization.toConfigMap(kubeTopic, cmPredicate), ar -> {});
        mockK8s.setDeleteResponse(topicName, null);
        mockTopicStore.setCreateTopicResponse(topicName, null)
                .create(privateTopic, ar-> {});
        mockTopicStore.setDeleteTopicResponse(topicName, null);


        Async async = context.async();

        op.reconcile(null, kubeTopic, kafkaTopic, privateTopic, reconcileResult -> {
            assertSucceeded(context, reconcileResult);
            mockKafka.assertNotExists(context, kubeTopic.getTopicName());
            mockTopicStore.assertNotExists(context, kubeTopic.getTopicName());
            mockK8s.assertNotExists(context, kubeTopic.getMapName());
            async.complete();
        });
    }