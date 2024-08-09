public void repairSurvivors()
    {
        int queued = 0;
        tmLog.info(m_whoami + "received all repair logs and is repairing surviving replicas.");
        for (Iv2RepairLogResponseMessage li : m_repairLogUnion) {
            for (Entry<Long, ReplicaRepairStruct> entry : m_replicaRepairStructs.entrySet()) {
                if  (entry.getValue().needs(li.getSpHandle())) {
                    ++queued;
                    tmLog.debug(m_whoami + "repairing " + entry.getKey() + ". Max seen " +
                            entry.getValue().m_maxSpHandleSeen + ". Repairing with " +
                            li.getSpHandle());
                    m_mailbox.repairReplicaWith(entry.getKey(), li);
                }
            }
        }
        tmLog.info(m_whoami + "finished queuing " + queued
                + " replica repair messages.");
        declareReadyAsLeader();
    }