@VisibleForTesting
  static TraceComponent loadTraceComponent(ClassLoader classLoader) {
    try {
      // Call Class.forName with literal string name of the class to help shading tools.
      return Provider.createInstance(
          Class.forName("io.opencensus.impl.trace.TraceComponentImpl", true, classLoader),
          TraceComponent.class);
    } catch (ClassNotFoundException e) {
      logger.log(
          Level.FINE,
          "Couldn't load full implementation for TraceComponent, now trying to load lite "
              + "implementation.",
          e);
    }
    try {
      // Call Class.forName with literal string name of the class to help shading tools.
      return Provider.createInstance(
          Class.forName("io.opencensus.impllite.trace.TraceComponentImplLite", true, classLoader),
          TraceComponent.class);
    } catch (ClassNotFoundException e) {
      logger.log(
          Level.FINE,
          "Couldn't load lite implementation for TraceComponent, now using "
              + "default implementation for TraceComponent.",
          e);
    }
    return TraceComponent.newNoopTraceComponent();
  }