  private void addMapping(String substring, int javaStartLine) {
    SourceOrigin sourceInfo = SourceOrigin.create(javaStartLine, JAVA_FILENAME);
    writer.addMapping(findRange(substring, sourceInfo), null);
  }