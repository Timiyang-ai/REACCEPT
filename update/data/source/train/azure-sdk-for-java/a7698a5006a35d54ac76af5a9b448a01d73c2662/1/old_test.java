@Test
    public void getPartitionProperties() {
        // Act & Assert
        for (String partitionId : expectedPartitionIds) {
            StepVerifier.create(client.getPartitionProperties(partitionId))
                .assertNext(properties -> {
                    Assert.assertEquals(eventHubPath, properties.eventHubPath());
                    Assert.assertEquals(partitionId, properties.id());
                })
                .verifyComplete();
        }
    }