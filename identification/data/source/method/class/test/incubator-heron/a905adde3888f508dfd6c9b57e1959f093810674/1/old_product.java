public void registerTimerEventInSeconds(long timerInSeconds, Runnable task) {
    nioLooper.registerTimerEventInSeconds(timerInSeconds, task);
  }