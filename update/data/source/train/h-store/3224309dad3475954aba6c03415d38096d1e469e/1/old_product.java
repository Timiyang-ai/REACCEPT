@Override
    public AbstractTransaction poll() {
        AbstractTransaction retval = null;
        
        // These invocations of poll() can return null if the next
        // txn was speculatively executed
        
        if (this.state == QueueState.BLOCKED_SAFETY) {
            if (EstTime.currentTimeMillis() >= this.blockTime) {
                retval = super.poll();
            }
        } else if (this.state == QueueState.UNBLOCKED) {
//            assert(checkQueueState() == QueueState.UNBLOCKED);
            retval = super.poll();
        }
        if (t) LOG.trace(String.format("Partition %d :: poll() -> %s", this.partitionId, retval));
        if (retval != null) {
            assert(this.nextTxn.equals(retval)) : 
                String.format("Partition %d :: Next txn is %s but our poll returned %s\n" +
                              StringUtil.SINGLE_LINE + "%s",
                              this.partitionId, this.nextTxn, retval, this.debug());
            this.nextTxn = null;
            this.txnsPopped++;
            this.lastTxnPopped = retval.getTransactionId();
        }
        
        if (d && retval != null)
            LOG.debug(String.format("Partition %d :: poll() -> %s", this.partitionId, retval));
        this.checkQueueState();
        return retval;
    }