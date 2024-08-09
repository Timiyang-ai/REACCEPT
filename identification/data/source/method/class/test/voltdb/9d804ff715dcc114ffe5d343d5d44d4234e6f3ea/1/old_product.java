public void repairSurvivors()
    {
        // cancel() and repair() must be synchronized by the caller (the deliver lock,
        // currently). If cancelled and the last repair message arrives, don't send
        // out corrections!
        if (this.m_promotionResult.isCancelled()) {
            tmLog.debug(m_whoami + "Skipping repair message creation for cancelled Term.");
            return;
        }

        int queued = 0;
        tmLog.info(m_whoami + "received all repair logs and is repairing surviving replicas.");
        for (Iv2RepairLogResponseMessage li : m_repairLogUnion) {
            List<Long> needsRepair = new ArrayList<Long>(5);
            for (Entry<Long, ReplicaRepairStruct> entry : m_replicaRepairStructs.entrySet()) {
                if  (entry.getValue().needs(li.getHandle())) {
                    ++queued;
                    tmLog.debug(m_whoami + "repairing " + entry.getKey() + ". Max seen " +
                            entry.getValue().m_maxSpHandleSeen + ". Repairing with " +
                            li.getHandle());
                    needsRepair.add(entry.getKey());
                }
            }
            if (!needsRepair.isEmpty()) {
                m_mailbox.repairReplicasWith(needsRepair, li);
            }
        }
        tmLog.info(m_whoami + "finished queuing " + queued + " replica repair messages.");

        m_promotionResult.done(m_maxSeenTxnId);
    }