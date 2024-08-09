public static int toInt(String num, int defaultValue) {
    if (num != null && !num.contains(" ")) {
      try (Scanner scanner = new Scanner(num)) {
        if (scanner.hasNextInt()) {
          return Integer.parseInt(num);
        }
      }
    }
    return defaultValue;
  }