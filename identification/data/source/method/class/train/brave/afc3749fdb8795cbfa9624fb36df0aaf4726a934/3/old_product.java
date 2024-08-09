public static void assertRunIsUnloadable(Class<? extends Runnable> runnable, ClassLoader parent) {
    // We can't use log4j2's log manager. More importantly, we want to make sure loggers don't hold
    // our test classloader from being collected.
    System.setProperty("java.util.logging.manager", LogManager.class.getName());
    assertThat(LogManager.getLogManager().getClass()).isSameAs(LogManager.class);

    WeakReference<ClassLoader> loader;
    try {
      loader = invokeRunFromNewClassLoader(runnable, parent);
    } catch (Exception e) {
      throw new AssertionError(e);
    }

    blockOnGC();

    assertThat(loader.get())
      .withFailMessage(runnable + " includes state that couldn't be garbage collected")
      .isNull();
  }