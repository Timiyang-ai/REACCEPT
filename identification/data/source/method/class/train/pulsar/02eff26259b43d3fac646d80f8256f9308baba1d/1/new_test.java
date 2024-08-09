@Test
    public void nonPersistentTopics() throws Exception {
        final String topicName = "nonPersistentTopic";

        final String persistentTopicName = "non-persistent://prop-xyz/ns1/" + topicName;
        // Force to create a topic
        publishMessagesOnTopic("non-persistent://prop-xyz/ns1/" + topicName, 0, 0);

        // create consumer and subscription
        URL pulsarUrl = new URL("http://127.0.0.1" + ":" + BROKER_WEBSERVICE_PORT);
        PulsarClient client = PulsarClient.builder().serviceUrl(pulsarUrl.toString()).statsInterval(0, TimeUnit.SECONDS)
                .build();
        Consumer<byte[]> consumer = client.newConsumer().topic(persistentTopicName).subscriptionName("my-sub")
                .subscribe();

        publishMessagesOnTopic("non-persistent://prop-xyz/ns1/" + topicName, 10, 0);

        TopicStats topicStats = admin.topics().getStats(persistentTopicName);
        assertEquals(topicStats.subscriptions.keySet(), Sets.newTreeSet(Lists.newArrayList("my-sub")));
        assertEquals(topicStats.subscriptions.get("my-sub").consumers.size(), 1);
        assertEquals(topicStats.publishers.size(), 0);

        PersistentTopicInternalStats internalStats = admin.topics().getInternalStats(persistentTopicName);
        assertEquals(internalStats.cursors.keySet(), Sets.newTreeSet(Lists.newArrayList("my-sub")));

        consumer.close();
        client.close();

        topicStats = admin.topics().getStats(persistentTopicName);
        assertTrue(topicStats.subscriptions.keySet().contains("my-sub"));
        assertEquals(topicStats.publishers.size(), 0);

        // test partitioned-topic
        final String partitionedTopicName = "non-persistent://prop-xyz/ns1/paritioned";
        assertEquals(admin.topics().getPartitionedTopicMetadata(partitionedTopicName).partitions, 0);
        admin.topics().createPartitionedTopic(partitionedTopicName, 5);
        assertEquals(admin.topics().getPartitionedTopicMetadata(partitionedTopicName).partitions, 5);
    }