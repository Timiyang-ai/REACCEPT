@Test
    public void testOnTopicCreated_retryTimeout(TestContext context) {
        TopicMetadata topicMetadata = getTopicMetadata();

        mockKafka.setTopicMetadataResponse(topicName, null, null);

        Async async = context.async();
        controller.onTopicCreated(topicName, ar -> {
            assertFailed(context, ar);
            context.assertEquals(ar.cause().getClass(), MaxAttemptsExceededException.class);
            mockK8s.assertNotExists(context, mapName);
            mockTopicStore.assertNotExists(context, topicName);
            async.complete();
        });
    }