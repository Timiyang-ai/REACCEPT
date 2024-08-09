@Test
    public void receive() {
        // Arrange
        final int numberOfEvents = 15;
        final String messageId = UUID.randomUUID().toString();
        final EventHubProducerAsyncClient producer = new EventHubClientBuilder()
            .connectionString(getConnectionString()).buildAsyncProducer();
        final EventHubConsumerClient receiver = new EventHubClientBuilder()
                .connectionString(getConnectionString())
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .startingPosition(EventPosition.earliest())
                .buildConsumer();

        producer.send(TestUtils.getEvents(numberOfEvents, messageId), sendOptions).block();

        // Act
        final IterableStream<PartitionEvent> receive = receiver.receive(PARTITION_ID, 15, Duration.ofSeconds(30));

        // Assert
        Assert.assertNotNull(receive);
        final List<PartitionEvent> results = receive.stream().collect(Collectors.toList());
        Assert.assertEquals(numberOfEvents, results.size());
    }