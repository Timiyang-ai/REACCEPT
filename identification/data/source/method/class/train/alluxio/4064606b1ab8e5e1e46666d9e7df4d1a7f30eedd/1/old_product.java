@Override
  public void heartbeat() {
    // Prepare metadata for the next heartbeat
    BlockHeartbeatReport blockReport = mBlockWorker.getReport();
    BlockStoreMeta storeMeta = mBlockWorker.getStoreMeta();

    // Send the heartbeat and execute the response
    Command cmdFromMaster = null;
    try {
      cmdFromMaster = mMasterClient
          .heartbeat(WorkerIdRegistry.getWorkerId(), storeMeta.getUsedBytesOnTiers(),
              blockReport.getRemovedBlocks(), blockReport.getAddedBlocks());
      handleMasterCommand(cmdFromMaster);
      mLastSuccessfulHeartbeatMs = System.currentTimeMillis();
    } catch (Exception e) {
      // An error occurred, log and ignore it or error if heartbeat timeout is reached
      if (cmdFromMaster == null) {
        LOG.error("Failed to receive master heartbeat command.", e);
      } else {
        LOG.error("Failed to receive or execute master heartbeat command: {}",
            cmdFromMaster.toString(), e);
      }
      mMasterClient.resetConnection();
      if (System.currentTimeMillis() - mLastSuccessfulHeartbeatMs >= mHeartbeatTimeoutMs) {
        throw new RuntimeException("Master heartbeat timeout exceeded: " + mHeartbeatTimeoutMs);
      }
    }
  }