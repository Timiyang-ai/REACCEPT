public URL getResourceAsURL(String resource, ClassLoader classLoader) {
    return getResourceAsURL(resource, new ClassLoader[]{
        classLoader,
        defaultClassLoader,
        Thread.currentThread().getContextClassLoader(),
        getClass().getClassLoader(),
        ClassLoader.getSystemClassLoader()
    });
  }