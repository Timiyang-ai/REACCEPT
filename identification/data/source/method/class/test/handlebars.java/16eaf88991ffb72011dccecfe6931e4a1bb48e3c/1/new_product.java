protected URL[] projectClasspath() throws Exception {
    @SuppressWarnings("unchecked")
    List<String> classpathElements = project.getRuntimeClasspathElements();
    final URL[] classpath = new URL[classpathElements.size()];
    for (int i = 0; i < classpath.length; i++) {
      classpath[i] = new File(classpathElements.get(i)).toURI().toURL();
    }
    return classpath;
  }