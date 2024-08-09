static Platform findPlatform() {
    // Find JRE 8 new types
    try {
      Class.forName("java.io.UncheckedIOException");
      return new Jre8(); // intentionally doesn't not access the type prior to the above guard
    } catch (ClassNotFoundException e) {
      // pre JRE 8
    }

    // Find JRE 7 new types
    try {
      Class.forName("java.util.concurrent.ThreadLocalRandom");
      return new Jre7(); // intentionally doesn't not access the type prior to the above guard
    } catch (ClassNotFoundException e) {
      // pre JRE 7
    }

    // compatible with JRE 6
    return Jre6.build();
  }