long parseMemoryLine(final String line) {
    final int idx1 = line.indexOf(":");
    final int idx2 = line.lastIndexOf("kB");
    final String sizeString = line.substring(idx1 + 1, idx2 - 1).trim();
    try {
      return Long.parseLong(sizeString);
    } catch (final NumberFormatException e) {
      final String err = "Failed to parse the meminfo file. Line: " + line;
      logger.error(err);
      return 0;
    }
  }