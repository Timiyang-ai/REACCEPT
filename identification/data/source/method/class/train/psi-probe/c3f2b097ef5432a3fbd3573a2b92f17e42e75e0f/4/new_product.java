public static String leftPad(String str, int len, String fill) {
    StringBuilder sb = new StringBuilder(len);
    if (str.length() < len) {
      for (int i = str.length(); i < len; i++) {
        sb.append(fill);
      }
    }
    sb.append(str);
    return sb.toString();
  }