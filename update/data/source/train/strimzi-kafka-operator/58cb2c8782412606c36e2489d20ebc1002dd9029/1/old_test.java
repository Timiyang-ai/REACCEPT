@Test
    public void testOnTopicCreated_retry(TestContext context) {
        TopicMetadata topicMetadata = Utils.getTopicMetadata(topicName.toString(),
                new org.apache.kafka.clients.admin.Config(Collections.emptyList()));

        mockTopicStore.setCreateTopicResponse(topicName, null);
        AtomicInteger counter = new AtomicInteger();
        mockKafka.setTopicMetadataResponse(t -> {
            int count = counter.getAndIncrement();
            if (count == 3) {
                return Future.succeededFuture(topicMetadata);
            } else if (count < 3) {
                return Future.succeededFuture(null);
            }
            context.fail("This should never happen");
            return Future.failedFuture("This should never happen");
        });
        mockK8s.setCreateResponse(resourceName, null);
        LogContext logContext = LogContext.zkWatch("///", topicName.toString());
        Async async = context.async();
        topicOperator.onTopicCreated(logContext, topicName, ar -> {
            assertSucceeded(context, ar);
            context.assertEquals(4, counter.get());
            mockK8s.assertExists(context, resourceName);
            mockTopicStore.assertContains(context, TopicSerialization.fromTopicMetadata(topicMetadata));
            async.complete();
        });
    }