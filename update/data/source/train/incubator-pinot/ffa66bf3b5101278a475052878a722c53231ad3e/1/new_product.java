public synchronized void invalidateCachedControllerLeader() {
    long now = getCurrentTimeMs();
    long millisSinceLastInvalidate = now - _lastCacheInvalidationTimeMs;
    if (millisSinceLastInvalidate < MIN_INVALIDATE_INTERVAL_MS) {
      LOGGER.info(
          "Millis since last controller cache value invalidate {} is less than allowed frequency {}. Skipping invalidate.",
          millisSinceLastInvalidate, MIN_INVALIDATE_INTERVAL_MS);
    } else {
      LOGGER.info("Invalidating cached controller leader value");
      _cachedControllerLeaderValid = false;
      _lastCacheInvalidationTimeMs = now;
    }
  }