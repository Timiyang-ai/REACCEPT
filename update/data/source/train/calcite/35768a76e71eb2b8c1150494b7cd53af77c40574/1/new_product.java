public static String trim(boolean leading, boolean trailing, String seek,
      String s) {
    return trim_(s, leading, trailing, seek.charAt(0));
  }