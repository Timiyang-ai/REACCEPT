public static int count(char c, String name) {

    int count = 0;
    for (int i = 0; i < name.length(); i++) {
      if (c == name.charAt(i)) {
        count++;
      }
    }
    return count;
  }