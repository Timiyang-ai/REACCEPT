Ticker ticker() {
    return isRecordingStats() || isExpirable()
        ? Ticker.systemTicker()
        : DISABLED_TICKER;
  }