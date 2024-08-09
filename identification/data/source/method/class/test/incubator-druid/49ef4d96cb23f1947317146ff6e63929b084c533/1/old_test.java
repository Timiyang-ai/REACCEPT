  private static List<String> splitGlob(String path)
  {
    return Lists.newArrayList(HadoopGlobPathSplitter.splitGlob(path));
  }