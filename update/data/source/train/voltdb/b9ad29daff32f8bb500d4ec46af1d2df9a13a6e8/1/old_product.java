public void repairSurvivors()
    {
        // cancel() and repair() must be synchronized by the caller (the deliver lock,
        // currently). If cancelled and the last repair message arrives, don't send
        // out corrections!
        if (this.m_promotionResult.isCancelled()) {
            tmLog.debug(m_whoami + "skipping repair message creation for cancelled Term.");
            return;
        }

        tmLog.debug(m_whoami + "received all repair logs and is repairing surviving replicas.");
        for (Iv2RepairLogResponseMessage li : m_repairLogUnion) {
            // send the repair log union to all the survivors. SPIs will ignore
            // CompleteTransactionMessages for transactions which have already
            // completed, so this has the effect of making sure that any holes
            // in the repair log are filled without explicitly having to
            // discover and track them.
            VoltMessage repairMsg = createRepairMessage(li);
            if (tmLog.isDebugEnabled()) {
                tmLog.debug(m_whoami + "repairing: " + CoreUtils.hsIdCollectionToString(m_survivors) + " with: " + TxnEgo.txnIdToString(li.getTxnId()) +
                        " " + repairMsg);
            }
            m_mailbox.repairReplicasWith(m_survivors, repairMsg);
        }

        m_promotionResult.set(new RepairResult(m_maxSeenTxnId));
    }