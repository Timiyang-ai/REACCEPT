public void updateStateAndSendEvent() {
    updateStateAndSendEvent(testBytesUsedForThresholdSet != -1 ? testBytesUsedForThresholdSet : getBytesUsed());
  }