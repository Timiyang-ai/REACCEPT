private void registerTimerEvent(Duration timer, Runnable task) {
    nioLooper.registerTimerEvent(timer, task);
  }