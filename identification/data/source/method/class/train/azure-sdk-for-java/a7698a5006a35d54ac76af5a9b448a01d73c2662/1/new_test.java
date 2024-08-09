@Test
    public void getEventHubProperties() {
        // Act & Assert
        StepVerifier.create(client.getProperties())
            .assertNext(properties -> {
                Assert.assertNotNull(properties);
                Assert.assertEquals(eventHubName, properties.name());
                Assert.assertEquals(expectedPartitionIds.length, properties.partitionIds().length);
            }).verifyComplete();
    }