@Test
    public void testOnConfigMapAdded_EntityExistsException(TestContext context) {
        configMapAdded(context,
                null,
                new TopicStore.EntityExistsException());
        // TODO what happens when we subsequently reconcile?
    }