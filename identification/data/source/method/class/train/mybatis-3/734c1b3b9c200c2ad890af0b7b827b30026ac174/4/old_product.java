public Class classForName(String name) throws ClassNotFoundException {
    return classForName(name, new ClassLoader[]{
        defaultClassLoader,
        Thread.currentThread().getContextClassLoader(),
        getClass().getClassLoader(),
        ClassLoader.getSystemClassLoader()
    });
  }