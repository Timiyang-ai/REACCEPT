long parseMemoryLine(String line) {
    int idx1 = line.indexOf(":");
    int idx2 = line.lastIndexOf("kB");
    String sizeString = line.substring(idx1 + 1, idx2 - 1).trim();
    try {
      return Long.parseLong(sizeString);
    } catch (NumberFormatException e) {
      String err = "Failed to parse the meminfo file. Line: " + line;
      logger.warn(err);
      return 0;
    }
  }