long getOsTotalFreeMemorySize() {
    List<String> lines;
    // The file /proc/meminfo seems to contain only ASCII characters.
    // The assumption is that the file is not too big. So it is simpler to read the whole file into memory.
    try {
      lines = Files.readAllLines(Paths.get(MEM_INFO_FILE), StandardCharsets.UTF_8);
    } catch (IOException e) {
      String errMsg = "Failed to open mem info file: " + MEM_INFO_FILE;
      logger.warn(errMsg, e);
      return 0;
    }
    return getOsTotalFreeMemorySizeFromStrings(lines);
  }