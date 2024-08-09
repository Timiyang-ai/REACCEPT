  @Test
  public void updateStateAndSendEvent_AboveCriticalMoreThanEventTolerance() {
    setupHeapMonitorThresholds(true, true);

    // It will take 4 consecutive events above the critical threshold to cause a state transition
    // given our memoryStateChangeTolerance of 3 in this test.
    sendEventAndAssertState(criticalUsedBytes, testMemoryEventTolerance,
        MemoryThresholds.MemoryState.NORMAL);
    sendEventAndAssertState(criticalUsedBytes, 1, MemoryThresholds.MemoryState.EVICTION_CRITICAL);
  }