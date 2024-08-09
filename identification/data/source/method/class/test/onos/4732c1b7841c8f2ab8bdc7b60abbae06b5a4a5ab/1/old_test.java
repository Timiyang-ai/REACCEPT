@Test
    public void testRelinquish() {
        // We have all the partitions so we'll need to relinquish some
        setUpLeadershipService(PartitionManager.NUM_PARTITIONS);

        expect(leadershipService.withdraw(anyString()))
                                .andReturn(CompletableFuture.completedFuture(null))
                                .times(7);

        replay(leadershipService);

        partitionManager.activate();
        // Send in the event
        leaderListener.event(event);

        verify(leadershipService);
    }