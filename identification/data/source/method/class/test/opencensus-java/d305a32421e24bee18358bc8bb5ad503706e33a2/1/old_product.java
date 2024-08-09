@VisibleForTesting
  @Nullable
  static StatsManager loadStatsManager(ClassLoader classLoader) {
    try {
      // Call Class.forName with literal string name of the class to help shading tools.
      return Provider.createInstance(
          Class.forName("io.opencensus.stats.StatsManagerImpl", true, classLoader),
          StatsManager.class);
    } catch (ClassNotFoundException e) {
      logger.log(
          Level.FINE,
          "Couldn't load full implementation for StatsManager, now trying to load lite "
              + "implementation.",
          e);
    }
    try {
      // Call Class.forName with literal string name of the class to help shading tools.
      return Provider.createInstance(
          Class.forName("io.opencensus.stats.StatsManagerImplLite", true, classLoader),
          StatsManager.class);
    } catch (ClassNotFoundException e) {
      logger.log(
          Level.FINE,
          "Couldn't load lite implementation for StatsManager, now using "
              + "default implementation for StatsManager.",
          e);
    }
    // TODO: Add a no-op implementation.
    return null;
  }