@Test
    public void getPartitionProperties() {
        final EventHubConsumerAsyncClient consumer = createBuilder()
            .consumerGroup(DEFAULT_CONSUMER_GROUP_NAME)
            .startingPosition(EventPosition.earliest())
            .buildAsyncConsumer();

        // Act & Assert
        try {
            for (String partitionId : expectedPartitionIds) {
                StepVerifier.create(consumer.getPartitionProperties(partitionId))
                    .assertNext(properties -> {
                        Assertions.assertEquals(consumer.getEventHubName(), properties.getEventHubName());
                        Assertions.assertEquals(partitionId, properties.getId());
                    })
                    .verifyComplete();
            }
        } finally {
            dispose(consumer);
        }
    }