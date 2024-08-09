  private static TargetPattern parse(String pattern) throws TargetParsingException {
    return TargetPattern.defaultParser().parse(pattern);
  }