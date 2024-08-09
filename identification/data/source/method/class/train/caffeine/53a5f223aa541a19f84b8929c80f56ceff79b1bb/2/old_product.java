void scheduleDrainBuffers() {
    if (evictionLock.tryLock()) {
      try {
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