public synchronized void advanceTime(Duration duration) {
    currentTime = validateNanos(currentTime.addDuration(duration));
  }