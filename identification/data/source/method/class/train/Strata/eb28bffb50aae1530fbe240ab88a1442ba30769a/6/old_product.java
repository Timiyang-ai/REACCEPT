public static void wrap(CheckedRunnable block) {
    try {
      block.run();
    } catch (IOException ex) {
      throw new UncheckedIOException(ex);
    } catch (Throwable ex) {
      throw Throwables.propagate(ex);
    }
  }