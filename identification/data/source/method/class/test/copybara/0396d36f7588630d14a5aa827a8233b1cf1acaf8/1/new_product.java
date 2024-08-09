public static void logLines(Console console, String prefix, String text) {
    consoleLogLines(prefix, text, console::info);
  }