  private static String substitute(String pathString) {
    Config config = Config.newBuilder()
        .put(Key.BUILD_HOST, "build_host")
        .put(Key.HERON_LIB, "/some/lib/dir")
        .put(Key.HERON_HOME, "/usr/local/heron")
        .put(Key.TOPOLOGY_NAME, "topology_name")
        .build();
    return TokenSub.substitute(config, pathString);
  }