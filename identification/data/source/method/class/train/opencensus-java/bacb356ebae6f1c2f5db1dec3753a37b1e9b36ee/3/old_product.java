@VisibleForTesting
  @Nullable
  static TagsComponent loadTagsComponent(ClassLoader classLoader) {
    try {
      // Call Class.forName with literal string name of the class to help shading tools.
      return Provider.createInstance(
          Class.forName("io.opencensus.tags.TagsComponentImpl", true, classLoader),
          TagsComponent.class);
    } catch (ClassNotFoundException e) {
      logger.log(
          Level.FINE,
          "Couldn't load full implementation for TagsComponent, now trying to load lite "
              + "implementation.",
          e);
    }
    try {
      // Call Class.forName with literal string name of the class to help shading tools.
      return Provider.createInstance(
          Class.forName("io.opencensus.tags.TagsComponentImplLite", true, classLoader),
          TagsComponent.class);
    } catch (ClassNotFoundException e) {
      logger.log(
          Level.FINE,
          "Couldn't load lite implementation for TagsComponent, now using "
              + "default implementation for TagsComponent.",
          e);
    }
    // TODO: Add a no-op implementation.
    return null;
  }