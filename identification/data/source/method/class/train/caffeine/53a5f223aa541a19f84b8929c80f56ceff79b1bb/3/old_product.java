void scheduleDrainBuffers() {
    if (evictionLock.tryLock()) {
      try {
        if (drainStatus() == PROCESSING) {
          return;
        }
        lazySetDrainStatus(PROCESSING);
        executor().execute(drainBuffersTask);
      } catch (Throwable t) {
        cleanUp();
        logger.log(Level.WARNING, "Exception thrown when submitting maintenance task", t);
      } finally {
        evictionLock.unlock();
      }
    }
  }