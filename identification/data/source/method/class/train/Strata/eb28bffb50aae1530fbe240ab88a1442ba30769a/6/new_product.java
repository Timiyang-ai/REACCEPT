public static void wrap(CheckedRunnable block) {
    try {
      block.run();
    } catch (Throwable ex) {
      throw propagate(ex);
    }
  }