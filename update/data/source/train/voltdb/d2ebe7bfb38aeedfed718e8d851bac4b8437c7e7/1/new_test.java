@SuppressWarnings("unchecked")
	@Test
    public void testRepairSurvivors()
    {
        InitiatorMailbox mailbox = mock(InitiatorMailbox.class);
        Term term = new Term(mock(ZooKeeper.class), 0, 0L, mailbox);

        // missing 4, 5
        Term.ReplicaRepairStruct r1 = new Term.ReplicaRepairStruct();
        r1.m_maxSpHandleSeen = 3L;

        // complete
        Term.ReplicaRepairStruct r2 = new Term.ReplicaRepairStruct();
        r2.m_maxSpHandleSeen = 5L;

        // missing 3, 4, 5
        Term.ReplicaRepairStruct r3 = new Term.ReplicaRepairStruct();
        r3.m_maxSpHandleSeen = 2L;

        term.m_replicaRepairStructs.put(1L, r1);
        term.m_replicaRepairStructs.put(2L, r2);
        term.m_replicaRepairStructs.put(3L, r3);

        // build the log
        long spHandles[] = new long[]{0L, 1L, 2L, 3L, 4L, 5L};
        Iv2RepairLogResponseMessage msgs[] = new Iv2RepairLogResponseMessage[6];
        for (int i=1; i < spHandles.length; ++i) {
            msgs[i] = makeResponse(spHandles[i]);
            term.m_repairLogUnion.add(msgs[i]);
        }

        // call the function being tested...
        term.repairSurvivors();

        List<Long> repair3 = new ArrayList<Long>();
        repair3.add(3L);
        verify(mailbox).repairReplicasWith(repair3, msgs[3]);

        List<Long> repair4And5 = new ArrayList<Long>();
        repair4And5.add(1L);
        repair4And5.add(3L);
        verify(mailbox).repairReplicasWith(repair4And5, msgs[4]);
        verify(mailbox).repairReplicasWith(repair4And5, msgs[5]);

        // verify exactly 3 repairs happened.
        verify(mailbox, times(3)).repairReplicasWith(any(repair3.getClass()), any(Iv2RepairLogResponseMessage.class));
    }