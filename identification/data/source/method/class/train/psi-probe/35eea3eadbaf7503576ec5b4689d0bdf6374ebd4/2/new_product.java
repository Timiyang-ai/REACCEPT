public static int toIntHex(String num, int defaultValue) {
    if (num != null && !num.contains(" ")) {
      if (num.startsWith("#")) {
        num = num.substring(1);
      }
      try (Scanner scanner = new Scanner(num)) {
        if (scanner.hasNextInt()) {
          return Integer.parseInt(num, 16);
        }
      }
    }
    return defaultValue;
  }