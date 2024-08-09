@Test
    public void receive() {
        // Arrange
        final int numberOfEvents = 15;
        final String messageId = UUID.randomUUID().toString();
        final EventHubAsyncClient asyncClient = new EventHubClientBuilder()
            .connectionString(getConnectionString())
            .buildAsyncClient();
        final EventHubProducerAsyncClient producer = asyncClient.createProducer();
        final EventHubConsumerClient receiver = client.createConsumer(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME,
            EventPosition.earliest());

        producer.send(TestUtils.getEvents(numberOfEvents, messageId), sendOptions).block();

        // Act
        final IterableStream<PartitionEvent> receive = receiver.receive(PARTITION_ID, 15, Duration.ofSeconds(30));

        // Assert
        Assert.assertNotNull(receive);
        final List<PartitionEvent> results = receive.stream().collect(Collectors.toList());
        Assert.assertEquals(numberOfEvents, results.size());
    }