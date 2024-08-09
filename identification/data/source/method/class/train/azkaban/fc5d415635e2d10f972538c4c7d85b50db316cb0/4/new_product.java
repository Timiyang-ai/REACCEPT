long getOsTotalFreeMemorySize() {
    if (!Files.isRegularFile(Paths.get(MEM_INFO_FILE))) {
      // Mac doesn't support /proc/meminfo for example.
      return 0;
    }

    final List<String> lines;
    // The file /proc/meminfo is assumed to contain only ASCII characters.
    // The assumption is that the file is not too big. So it is simpler to read the whole file into memory.
    try {
      lines = Files.readAllLines(Paths.get(MEM_INFO_FILE), StandardCharsets.UTF_8);
    } catch (final IOException e) {
      final String errMsg = "Failed to open mem info file: " + MEM_INFO_FILE;
      logger.error(errMsg, e);
      return 0;
    }
    return getOsTotalFreeMemorySizeFromStrings(lines);
  }