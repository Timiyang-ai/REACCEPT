public synchronized void invalidateCachedControllerLeader() {
    long now = System.currentTimeMillis();
    long millisSinceLastInvalidate = now - _lastCacheInvalidateMillis;
    if (millisSinceLastInvalidate < MILLIS_BETWEEN_INVALIDATE) {
      LOGGER.info(
          "Millis since last controller cache value invalidate {} is less than allowed frequency {}. Skipping invalidate.",
          millisSinceLastInvalidate, MILLIS_BETWEEN_INVALIDATE);
    } else {
      LOGGER.info("Invalidating cached controller leader value");
      _cachedControllerLeaderInvalid = true;
      _lastCacheInvalidateMillis = now;
    }
  }