void scheduleDrainBuffers() {
    if (drainStatus() >= PROCESSING_TO_IDLE) {
      return;
    }
    if (evictionLock.tryLock()) {
      try {
        int drainStatus = drainStatus();
        if (drainStatus >= PROCESSING_TO_IDLE) {
          return;
        }
        lazySetDrainStatus(PROCESSING_TO_IDLE);
        executor().execute(drainBuffersTask);
      } catch (Throwable t) {
        logger.log(Level.WARNING, "Exception thrown when submitting maintenance task", t);
        maintenance(/* ignored */ null);
      } finally {
        evictionLock.unlock();
      }
    }
  }