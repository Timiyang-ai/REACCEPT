@Test
    public void getPartitionIds() {
        final EventHubConsumerClient consumer = createBuilder()
            .consumerGroup(DEFAULT_CONSUMER_GROUP_NAME)
            .startingPosition(EventPosition.earliest())
            .buildConsumer();

        // Act & Assert
        try {
            final IterableStream<String> partitionIds = consumer.getPartitionIds();
            final List<String> collect = partitionIds.stream().collect(Collectors.toList());

            Assertions.assertEquals(2, collect.size());
        } finally {
            dispose(consumer);
        }
    }