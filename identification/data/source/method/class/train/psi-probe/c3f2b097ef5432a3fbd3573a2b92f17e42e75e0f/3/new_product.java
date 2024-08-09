public static long toLong(String num, long defaultValue) {
    if (num != null && !num.contains(" ")) {
      try (Scanner scanner = new Scanner(num)) {
        if (scanner.hasNextLong()) {
          return Long.parseLong(num);
        }
      }
    }
    return defaultValue;
  }