@Test
    public void testRepairSurvivors() throws InterruptedException, ExecutionException
    {
        System.out.println("Running testRepairSurvivors");
        InitiatorMailbox mailbox = mock(MpInitiatorMailbox.class);
        doReturn(4L).when(mailbox).getHSId();
        ArrayList<Long> masters = new ArrayList<Long>();
        masters.add(1L);
        masters.add(2L);
        masters.add(3L);

        MpPromoteAlgo algo = new MpPromoteAlgo(masters, mailbox, "Test");
        long requestId = algo.getRequestId();
        Future<Pair<Long, Long>> result = algo.start();
        verify(mailbox, times(1)).send(any(long[].class), any(Iv2RepairLogRequestMessage.class));

        final long uid = uig.getNextUniqueId();

        // has a frag for txn 1000. MP handle is 1000L
        algo.deliver(makeRealAckResponse(requestId, 1L, 0, 2, txnEgo(1000L), m_hashinatorConfig, uid));
        algo.deliver(makeRealFragResponse(requestId, 1L, 1, 2, txnEgo(1000L)));

        // has only the normal ack. Never saw an MP transaction.
        algo.deliver(makeRealAckResponse(requestId, 2L, 0, 1, Long.MAX_VALUE, m_hashinatorConfig, uid));

        // also has a complete. MP handle is 1000L
        // and deliver a newer version of the hashinator config
        Pair<Long,byte[]> torv3 = Pair.of(
                m_hashinatorConfig.getFirst()+1,
                m_hashinatorConfig.getSecond()
                );
        algo.deliver(makeRealAckResponse(requestId, 3L, 0, 3, txnEgo(1000L), torv3, uid));
        algo.deliver(makeRealFragResponse(requestId, 3L, 1, 3, txnEgo(1000L)));
        algo.deliver(makeRealCompleteResponse(requestId, 3L, 2, 3, txnEgo(1000L)));

        // deliver the same complete from the MPI's repair log
        algo.deliver(makeRealAckResponse(requestId, 4L, 0, 2, txnEgo(1000L), m_hashinatorConfig, uid));
        algo.deliver(makeRealCompleteResponse(requestId, 4L, 1, 2, txnEgo(1000L)));

        // Verify that we send a complete to every site.
        List<Long> needsRepair = new ArrayList<Long>();
        needsRepair.add(1L);
        needsRepair.add(2L);
        needsRepair.add(3L);
        verify(mailbox, times(1)).repairReplicasWith(eq(needsRepair), any(Iv2RepairLogResponseMessage.class));
        Long real_result = result.get().getFirst();
        assertEquals(txnEgo(1000L), (long)real_result);
        assertEquals(new Long(uid), result.get().getSecond());

        // check if the hashinator was updated to the newer version
        assertEquals(torv3.getFirst(), TheHashinator.getCurrentVersionedConfig().getFirst());
    }