@Test
    public void receive() {
        // Arrange
        final int numberOfEvents = 15;
        final String messageId = UUID.randomUUID().toString();
        final EventHubAsyncClient asyncClient = new EventHubClientBuilder()
            .connectionString(getConnectionString())
            .buildAsyncClient();
        final EventHubProducerAsyncClient producer = asyncClient.createProducer();
        final EventHubConsumer receiver = client.createConsumer(EventHubAsyncClient.DEFAULT_CONSUMER_GROUP_NAME,
            PARTITION_ID, EventPosition.earliest());

        producer.send(TestUtils.getEvents(numberOfEvents, messageId), sendOptions).block();

        // Act
        final IterableStream<EventData> receive = receiver.receive(15, Duration.ofSeconds(30));

        // Assert
        Assert.assertNotNull(receive);
        final List<EventData> results = receive.stream().collect(Collectors.toList());
        Assert.assertEquals(numberOfEvents, results.size());
    }