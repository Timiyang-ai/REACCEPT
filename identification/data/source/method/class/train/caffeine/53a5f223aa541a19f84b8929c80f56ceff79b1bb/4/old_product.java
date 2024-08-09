void scheduleDrainBuffers() {
    if (drainStatus() == PROCESSING) {
      return;
    }
    if (evictionLock.tryLock()) {
      try {
        if (drainStatus() == PROCESSING) {
          return;
        }
        lazySetDrainStatus(PROCESSING);
        executor().execute(drainBuffersTask);
      } catch (Throwable t) {
        logger.log(Level.WARNING, "Exception thrown when submitting maintenance task", t);
        performCleanUp();
      } finally {
        evictionLock.unlock();
      }
    }
  }