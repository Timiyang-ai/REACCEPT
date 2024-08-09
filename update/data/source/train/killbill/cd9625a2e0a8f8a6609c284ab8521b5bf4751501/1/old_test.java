@Test(groups = "slow", description = "Verify we don't insert duplicate blocking states")
    public void testSetBlockingState() throws Exception {
        final UUID blockableId = UUID.randomUUID();
        final BlockingStateType type = BlockingStateType.ACCOUNT;
        final String state = "state";
        final String state2 = "state-2";
        final String serviceA = "service-A";
        final String serviceB = "service-B";

        // Verify initial state
        Assert.assertEquals(blockingStateDao.getBlockingAllForAccountRecordId(internalCallContext).size(), 0);

        // Note: the checkers below rely on record_id ordering, not effective date

        // Set a state for service A
        final DateTime stateDateTime = new DateTime(2013, 5, 6, 10, 11, 12, DateTimeZone.UTC);
        final BlockingState blockingState1 = new DefaultBlockingState(blockableId, type, state, serviceA, false, false, false, stateDateTime);
        blockingStateDao.setBlockingState(blockingState1, clock, internalCallContext);
        final List<BlockingState> blockingStates1 = blockingStateDao.getBlockingAllForAccountRecordId(internalCallContext);
        Assert.assertEquals(blockingStates1.size(), 1);
        Assert.assertEquals(blockingStates1.get(0).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates1.get(0).getStateName(), state);
        Assert.assertEquals(blockingStates1.get(0).getService(), serviceA);
        Assert.assertEquals(blockingStates1.get(0).getEffectiveDate(), stateDateTime);

        // Set the same state again - no change
        blockingStateDao.setBlockingState(blockingState1, clock, internalCallContext);
        final List<BlockingState> blockingStates2 = blockingStateDao.getBlockingAllForAccountRecordId(internalCallContext);
        Assert.assertEquals(blockingStates2.size(), 1);
        Assert.assertEquals(blockingStates2.get(0).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates2.get(0).getStateName(), state);
        Assert.assertEquals(blockingStates2.get(0).getService(), serviceA);
        Assert.assertEquals(blockingStates2.get(0).getEffectiveDate(), stateDateTime);

        // Set the state for service B
        final BlockingState blockingState2 = new DefaultBlockingState(blockableId, type, state, serviceB, false, false, false, stateDateTime);
        blockingStateDao.setBlockingState(blockingState2, clock, internalCallContext);
        final List<BlockingState> blockingStates3 = blockingStateDao.getBlockingAllForAccountRecordId(internalCallContext);
        Assert.assertEquals(blockingStates3.size(), 2);
        Assert.assertEquals(blockingStates3.get(0).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates3.get(0).getStateName(), state);
        Assert.assertEquals(blockingStates3.get(0).getService(), serviceA);
        Assert.assertEquals(blockingStates3.get(0).getEffectiveDate(), stateDateTime);
        Assert.assertEquals(blockingStates3.get(1).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates3.get(1).getStateName(), state);
        Assert.assertEquals(blockingStates3.get(1).getService(), serviceB);
        Assert.assertEquals(blockingStates3.get(1).getEffectiveDate(), stateDateTime);

        // Set the state for service A in the future - there should be no change (already effective)
        final DateTime stateDateTime2 = new DateTime(2013, 6, 6, 10, 11, 12, DateTimeZone.UTC);
        final BlockingState blockingState3 = new DefaultBlockingState(blockableId, type, state, serviceA, false, false, false, stateDateTime2);
        blockingStateDao.setBlockingState(blockingState3, clock, internalCallContext);
        final List<BlockingState> blockingStates4 = blockingStateDao.getBlockingAllForAccountRecordId(internalCallContext);
        Assert.assertEquals(blockingStates4.size(), 2);
        Assert.assertEquals(blockingStates4.get(0).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates4.get(0).getStateName(), state);
        Assert.assertEquals(blockingStates4.get(0).getService(), serviceA);
        Assert.assertEquals(blockingStates4.get(0).getEffectiveDate(), stateDateTime);
        Assert.assertEquals(blockingStates4.get(1).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates4.get(1).getStateName(), state);
        Assert.assertEquals(blockingStates4.get(1).getService(), serviceB);
        Assert.assertEquals(blockingStates4.get(1).getEffectiveDate(), stateDateTime);

        // Set the state for service A in the past - the new effective date should be respected
        final DateTime stateDateTime3 = new DateTime(2013, 2, 6, 10, 11, 12, DateTimeZone.UTC);
        final BlockingState blockingState4 = new DefaultBlockingState(blockableId, type, state, serviceA, false, false, false, stateDateTime3);
        blockingStateDao.setBlockingState(blockingState4, clock, internalCallContext);
        final List<BlockingState> blockingStates5 = blockingStateDao.getBlockingAllForAccountRecordId(internalCallContext);
        Assert.assertEquals(blockingStates5.size(), 2);
        Assert.assertEquals(blockingStates5.get(0).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates5.get(0).getStateName(), state);
        Assert.assertEquals(blockingStates5.get(0).getService(), serviceA);
        Assert.assertEquals(blockingStates5.get(0).getEffectiveDate(), stateDateTime3);
        Assert.assertEquals(blockingStates5.get(1).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates5.get(1).getStateName(), state);
        Assert.assertEquals(blockingStates5.get(1).getService(), serviceB);
        Assert.assertEquals(blockingStates5.get(1).getEffectiveDate(), stateDateTime);

        // Set a new state for service A
        final DateTime state2DateTime = new DateTime(2013, 12, 6, 10, 11, 12, DateTimeZone.UTC);
        final BlockingState blockingState5 = new DefaultBlockingState(blockableId, type, state2, serviceA, false, false, false, state2DateTime);
        blockingStateDao.setBlockingState(blockingState5, clock, internalCallContext);
        final List<BlockingState> blockingStates6 = blockingStateDao.getBlockingAllForAccountRecordId(internalCallContext);
        Assert.assertEquals(blockingStates6.size(), 3);
        Assert.assertEquals(blockingStates6.get(0).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates6.get(0).getStateName(), state);
        Assert.assertEquals(blockingStates6.get(0).getService(), serviceA);
        Assert.assertEquals(blockingStates6.get(0).getEffectiveDate(), stateDateTime3);
        Assert.assertEquals(blockingStates6.get(1).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates6.get(1).getStateName(), state);
        Assert.assertEquals(blockingStates6.get(1).getService(), serviceB);
        Assert.assertEquals(blockingStates6.get(1).getEffectiveDate(), stateDateTime);
        Assert.assertEquals(blockingStates6.get(2).getBlockedId(), blockableId);
        Assert.assertEquals(blockingStates6.get(2).getStateName(), state2);
        Assert.assertEquals(blockingStates6.get(2).getService(), serviceA);
        Assert.assertEquals(blockingStates6.get(2).getEffectiveDate(), state2DateTime);
    }