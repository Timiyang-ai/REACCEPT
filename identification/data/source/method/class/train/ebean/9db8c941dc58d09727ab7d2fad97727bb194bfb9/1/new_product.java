public static int count(String name) {

    int count = 0;
    for (int i = 0; i < name.length(); i++) {
      if (PERIOD == name.charAt(i)) {
        count++;
      }
    }
    return count;
  }