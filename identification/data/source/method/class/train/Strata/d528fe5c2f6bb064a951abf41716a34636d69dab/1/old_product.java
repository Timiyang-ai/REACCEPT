public static ExampleMarketDataBuilder ofResource(String resourceRoot, ClassLoader classLoader) {
    // classpath resources are forward-slash separated
    String qualifiedRoot = resourceRoot;
    qualifiedRoot = qualifiedRoot.startsWith("/") ? qualifiedRoot.substring(1) : qualifiedRoot;
    qualifiedRoot = qualifiedRoot.startsWith("\\") ? qualifiedRoot.substring(1) : qualifiedRoot;
    qualifiedRoot = qualifiedRoot.endsWith("/") ? qualifiedRoot : qualifiedRoot + "/";
    URL url = classLoader.getResource(qualifiedRoot);
    if (url == null) {
      throw new IllegalArgumentException(Messages.format("Classpath resource not found: {}", qualifiedRoot));
    }
    if (url.getProtocol() != null && "jar".equals(url.getProtocol().toLowerCase())) {
      // Inside a JAR
      int classSeparatorIdx = url.getFile().indexOf("!");
      if (classSeparatorIdx == -1) {
        throw new IllegalArgumentException(Messages.format("Unexpected JAR file URL: {}", url));
      }
      String jarPath = url.getFile().substring("file:".length(), classSeparatorIdx);
      File jarFile;
      try {
        jarFile = new File(jarPath);
      } catch (Exception e) {
        throw new IllegalArgumentException(Messages.format("Unable to create file for JAR: {}", jarPath), e);
      }
      return new JarMarketDataBuilder(jarFile, resourceRoot);
    } else {
      // Resource is on disk
      File file;
      try {
        file = new File(url.toURI());
      } catch (URISyntaxException e) {
        throw new IllegalArgumentException(Messages.format("Unexpected file location: {}", url), e);
      }
      return new DirectoryMarketDataBuilder(file.toPath());
    }
  }