@Override
  public void setup(PortContext context)
  {
    if (Boolean.getBoolean(THREAD_AFFINITY_DISABLE_CHECK) == false) {
      operatorThread = Thread.currentThread();
      logger.debug("Enforcing emit on {}", operatorThread.getName());
    }
  }