public URL getResourceAsURL(String resource) {
    return getResourceAsURL(resource, new ClassLoader[]{
        defaultClassLoader,
        Thread.currentThread().getContextClassLoader(),
        getClass().getClassLoader(),
        ClassLoader.getSystemClassLoader()
    });
  }