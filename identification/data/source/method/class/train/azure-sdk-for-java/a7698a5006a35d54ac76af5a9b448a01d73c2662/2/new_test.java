@Test
    public void getPartitionProperties() {
        // Act & Assert
        for (String partitionId : expectedPartitionIds) {
            StepVerifier.create(client.getPartitionProperties(partitionId))
                .assertNext(properties -> {
                    Assert.assertEquals(eventHubName, properties.eventHubName());
                    Assert.assertEquals(partitionId, properties.id());
                })
                .verifyComplete();
        }
    }