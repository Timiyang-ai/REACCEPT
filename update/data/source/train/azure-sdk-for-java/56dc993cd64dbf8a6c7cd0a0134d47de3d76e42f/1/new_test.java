@Test
    public void getPartitionIds() {
        // Act
        final IterableStream<String> response = client.getPartitionIds();

        // Assert
        Assert.assertNotNull(response);

        final List<String> partitionIds = response.stream().collect(Collectors.toList());
        Assert.assertTrue(partitionIds.size() > 1);
    }