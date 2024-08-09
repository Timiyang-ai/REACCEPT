@Override
    public Long poll() {
        Long retval = null;
        while (true) {
            if (this.state == QueueState.UNBLOCKED ||
                (this.state == QueueState.BLOCKED_SAFETY && EstTime.currentTimeMillis() >= this.blockTime)) {
                retval = super.poll();
                if (retval != null && this.removed.contains(retval)) {
                    this.removed.remove(super.poll());
                    this.checkQueueState();
                    continue;
                }
            }
            break;
        } // WHILE
        
        if (t) LOG.trace(String.format("Partition %d :: poll() -> %s", this.partitionId, retval));
        if (retval != null) {
            assert(this.nextTxnId.equals(retval)) : 
                String.format("Partition %d :: Next txn is %s but our poll returned %s\n" +
                              StringUtil.SINGLE_LINE + "%s",
                              this.partitionId, this.nextTxnId, retval, this.debug());
            this.nextTxnId = null;
            this.lastTxnIdPopped = retval;
        }
        
        if (d && retval != null)
            LOG.debug(String.format("Partition %d :: poll() -> %s", this.partitionId, retval));
        this.checkQueueState();
        return retval;
    }