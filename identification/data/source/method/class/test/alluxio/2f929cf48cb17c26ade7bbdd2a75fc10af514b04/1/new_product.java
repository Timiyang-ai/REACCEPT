public static String formatMode(short mode, boolean directory) {
    StringBuffer str = new StringBuffer();
    if (directory) {
      str.append("d");
    } else {
      str.append("-");
    }
    str.append(new Mode(mode).toString());
    return str.toString();
  }