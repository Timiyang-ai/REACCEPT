@Test
    public void testOnKafkaTopicAdded_EntityExistsException(TestContext context) {
        resourceAdded(context,
                null,
                new TopicStore.EntityExistsException());
        // TODO what happens when we subsequently reconcile?
    }