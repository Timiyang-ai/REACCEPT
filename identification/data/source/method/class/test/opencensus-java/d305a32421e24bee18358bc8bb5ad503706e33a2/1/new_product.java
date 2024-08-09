@VisibleForTesting
  @Nullable
  static StatsComponent loadStatsComponent(ClassLoader classLoader) {
    try {
      // Call Class.forName with literal string name of the class to help shading tools.
      return Provider.createInstance(
          Class.forName("io.opencensus.stats.StatsComponentImpl", true, classLoader),
          StatsComponent.class);
    } catch (ClassNotFoundException e) {
      logger.log(
          Level.FINE,
          "Couldn't load full implementation for StatsComponent, now trying to load lite "
              + "implementation.",
          e);
    }
    try {
      // Call Class.forName with literal string name of the class to help shading tools.
      return Provider.createInstance(
          Class.forName("io.opencensus.stats.StatsComponentImplLite", true, classLoader),
          StatsComponent.class);
    } catch (ClassNotFoundException e) {
      logger.log(
          Level.FINE,
          "Couldn't load lite implementation for StatsComponent, now using "
              + "default implementation for StatsComponent.",
          e);
    }
    // TODO: Add a no-op implementation.
    return null;
  }