@Test
    public void getEventHubProperties() {
        // Act & Assert
        StepVerifier.create(client.getProperties())
            .assertNext(properties -> {
                Assert.assertNotNull(properties);
                Assert.assertEquals(eventHubPath, properties.path());
                Assert.assertEquals(expectedPartitionIds.length, properties.partitionIds().length);
            }).verifyComplete();
    }