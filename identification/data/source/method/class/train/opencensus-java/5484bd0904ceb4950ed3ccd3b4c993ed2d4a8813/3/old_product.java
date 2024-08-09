@VisibleForTesting
  static TraceComponent loadTraceComponent(ClassLoader classLoader) {
    try {
      // Call Class.forName with literal string name of the class to help shading tools.
      return Provider.createInstance(
          Class.forName("io.opencensus.trace.TraceComponentImpl", true, classLoader),
          TraceComponent.class);
    } catch (ClassNotFoundException e) {
      logger.log(Level.FINE, "Using default implementation for TraceComponent.", e);
    }
    return TraceComponent.getNoopTraceComponent();
  }