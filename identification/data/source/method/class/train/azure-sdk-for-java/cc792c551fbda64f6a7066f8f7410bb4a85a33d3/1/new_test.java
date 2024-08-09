@Test
    public void receive() {
        // Arrange
        final int numberOfEvents = 15;
        final String messageId = UUID.randomUUID().toString();
        final EventHubProducerAsyncClient producer = new EventHubClientBuilder()
            .connectionString(getConnectionString()).buildAsyncProducerClient();
        final EventHubConsumerClient receiver = new EventHubClientBuilder()
                .connectionString(getConnectionString())
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .buildConsumerClient();

        producer.send(TestUtils.getEvents(numberOfEvents, messageId), sendOptions).block();

        // Act
        final IterableStream<PartitionEvent> receive = receiver.receiveFromPartition(PARTITION_ID, 15, EventPosition.earliest(), Duration.ofSeconds(30));

        // Assert
        Assertions.assertNotNull(receive);
        final List<PartitionEvent> results = receive.stream().collect(Collectors.toList());
        Assertions.assertEquals(numberOfEvents, results.size());
    }