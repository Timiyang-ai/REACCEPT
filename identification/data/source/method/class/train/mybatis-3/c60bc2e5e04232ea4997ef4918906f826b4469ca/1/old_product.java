public InputStream getResourceAsStream(String resource) {
    return getResourceAsStream(resource, new ClassLoader[]{
        defaultClassLoader,
        Thread.currentThread().getContextClassLoader(),
        getClass().getClassLoader(),
        ClassLoader.getSystemClassLoader()
    });
  }