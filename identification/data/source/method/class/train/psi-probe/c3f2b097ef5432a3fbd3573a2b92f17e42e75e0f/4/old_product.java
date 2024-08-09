public static String leftPad(String str, int len, String fill) {
    StringBuffer sb = new StringBuffer(len);
    if (str.length() < len) {
      for (int i = str.length(); i < len; i++) {
        sb.append(fill);
      }
    }
    sb.append(str);
    return sb.toString();
  }