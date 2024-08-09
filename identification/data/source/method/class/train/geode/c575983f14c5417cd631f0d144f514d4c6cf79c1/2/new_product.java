public void updateStateAndSendEvent(long bytesUsed, String eventOrigin) {
    this.stats.changeTenuredHeapUsed(bytesUsed);
    synchronized (this) {
      MemoryState oldState = this.mostRecentEvent.getState();
      MemoryState newState = this.thresholds.computeNextState(oldState, bytesUsed);
      if (oldState != newState) {
        setUsageThresholdOnMXBean(bytesUsed);

        this.currentState = newState;

        MemoryEvent event = new MemoryEvent(ResourceType.HEAP_MEMORY, oldState, newState,
            this.cache.getMyId(), bytesUsed, true, this.thresholds);

        this.upcomingEvent.set(event);
        processLocalEvent(event, eventOrigin);
        updateStatsFromEvent(event);

        // The state didn't change. However, if the state isn't normal and the
        // number of bytes used changed, then go ahead and send the event
        // again with an updated number of bytes used.
      } else if (!oldState.isNormal() && bytesUsed != this.mostRecentEvent.getBytesUsed()) {
        MemoryEvent event = new MemoryEvent(ResourceType.HEAP_MEMORY, oldState, newState,
            this.cache.getMyId(), bytesUsed, true, this.thresholds);
        this.upcomingEvent.set(event);
        processLocalEvent(event, eventOrigin);
      }
    }
  }