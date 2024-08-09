@Test
    public void testRepairSurvivors()
    {
        InitiatorMailbox mailbox = mock(InitiatorMailbox.class);
        Term term = new Term(null, 0, 0L, mailbox);

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

        // verify that r1 saw 4 and 5
        verify(mailbox).repairReplicaWith(1L, msgs[4]);
        verify(mailbox).repairReplicaWith(1L, msgs[5]);

        // verify that r3 saw 3, 4, 5
        verify(mailbox).repairReplicaWith(3L, msgs[3]);
        verify(mailbox).repairReplicaWith(3L, msgs[4]);
        verify(mailbox).repairReplicaWith(3L, msgs[5]);

        // verify exactly 5 repairs happened.
        verify(mailbox, times(5)).repairReplicaWith(anyLong(), any(Iv2RepairLogResponseMessage.class));
    }