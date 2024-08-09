public static String leftPad(String str, int len, String fill) {
    if (str != null && str.length() < len) {
      return Strings.padStart(str, len, fill.charAt(0));
    }
    return str;
  }