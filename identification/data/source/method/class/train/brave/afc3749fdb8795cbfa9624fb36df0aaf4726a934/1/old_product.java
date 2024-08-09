public static void assertRunIsUnloadable(Class<? extends Runnable> runnable, ClassLoader parent) {
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