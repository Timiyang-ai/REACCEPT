public static void logLines(Console console, String prefix, String text) {
    Iterator<String> lines = Splitter.on('\n').split(text).iterator();
    while (lines.hasNext()) {
      String line = lines.next();
      if (line.isEmpty() && !lines.hasNext()) {
        break;
      }
      console.info(prefix + line);
    }
  }