@Test
    public void testRepairSurvivors()
    {
        InitiatorMailbox mailbox = mock(InitiatorMailbox.class);
        ZooKeeper zk = mock(ZooKeeper.class);
        ArrayList<Long> masters = new ArrayList<Long>();
        masters.add(1L);
        masters.add(2L);
        masters.add(3L);

        MpPromoteAlgo algo = new MpPromoteAlgo(masters, zk, -1, mailbox, VoltZK.iv2masters, "Test");
        long requestId = algo.getRequestId();
        algo.prepareForFaultRecovery();
        verify(mailbox, times(1)).send(any(long[].class), any(Iv2RepairLogRequestMessage.class));

        // has a frag for txn 1000. MP handle is 1000L
        algo.deliver(makeRealAckResponse(requestId, 1L, 0, 2, 1000L));
        algo.deliver(makeRealFragResponse(requestId, 1L, 1, 2, 1000L));

        // has only the normal ack. Never saw an MP transaction.
        algo.deliver(makeRealAckResponse(requestId, 2L, 0, 1, Long.MAX_VALUE));

        // also has a complete. MP handle is 1000L
        algo.deliver(makeRealAckResponse(requestId, 3L, 0, 3, 1000L));
        algo.deliver(makeRealFragResponse(requestId, 3L, 1, 3, 1000L));
        algo.deliver(makeRealCompleteResponse(requestId, 3L, 2, 3, 1000L));

        // verify exactly 1 repair happened.
        List<Long> needsRepair = new ArrayList<Long>();
        needsRepair.add(1L);
        verify(mailbox, times(1)).repairReplicasWith(eq(needsRepair), any(Iv2RepairLogResponseMessage.class));
    }