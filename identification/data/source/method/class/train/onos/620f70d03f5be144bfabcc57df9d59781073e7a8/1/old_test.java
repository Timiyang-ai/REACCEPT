@Test
    public void testRebalance() {
        // We have all the partitions so we'll need to relinquish some
        setUpLeadershipService(IntentPartitionManager.NUM_PARTITIONS);

        expect(leadershipService.withdraw(anyString()))
                                 .andReturn(CompletableFuture.completedFuture(null))
                                 .times(7);

        replay(leadershipService);

        partitionManager.activate();

        // trigger rebalance
        partitionManager.doRebalance();

        verify(leadershipService);
    }