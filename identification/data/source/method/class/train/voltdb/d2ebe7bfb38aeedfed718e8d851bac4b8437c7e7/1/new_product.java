public void repairSurvivors()
    {
        int queued = 0;
        tmLog.info(m_whoami + "received all repair logs and is repairing surviving replicas.");
        for (Iv2RepairLogResponseMessage li : m_repairLogUnion) {
            List<Long> needsRepair = new ArrayList<Long>(5);
            for (Entry<Long, ReplicaRepairStruct> entry : m_replicaRepairStructs.entrySet()) {
                if  (entry.getValue().needs(li.getSpHandle())) {
                    ++queued;
                    tmLog.debug(m_whoami + "repairing " + entry.getKey() + ". Max seen " +
                            entry.getValue().m_maxSpHandleSeen + ". Repairing with " +
                            li.getSpHandle());
                    needsRepair.add(entry.getKey());
                }
            }
            if (!needsRepair.isEmpty()) {
                m_mailbox.repairReplicasWith(needsRepair, li);
            }
        }
        tmLog.info(m_whoami + "finished queuing " + queued + " replica repair messages.");
        declareReadyAsLeader();
    }