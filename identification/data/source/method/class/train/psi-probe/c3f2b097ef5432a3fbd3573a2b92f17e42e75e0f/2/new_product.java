public static float toFloat(String num, float defaultValue) {
    if (num != null && !num.contains(" ")) {
      try (Scanner scanner = new Scanner(num)) {
        if (scanner.hasNextFloat()) {
          return Float.parseFloat(num);
        }
      }
    }
    return defaultValue;
  }