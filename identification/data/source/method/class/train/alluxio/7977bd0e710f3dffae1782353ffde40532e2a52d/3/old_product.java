public static String getSizeFromBytes(long bytes) {
    double ret = bytes;
    if (ret <= 1024 * 5) {
      return String.format(Locale.ENGLISH, "%.2fB", ret);
    }
    ret /= 1024;
    if (ret <= 1024 * 5) {
      return String.format(Locale.ENGLISH, "%.2fKB", ret);
    }
    ret /= 1024;
    if (ret <= 1024 * 5) {
      return String.format(Locale.ENGLISH, "%.2fMB", ret);
    }
    ret /= 1024;
    if (ret <= 1024 * 5) {
      return String.format(Locale.ENGLISH, "%.2fGB", ret);
    }
    ret /= 1024;
    if (ret <= 1024 * 5) {
      return String.format(Locale.ENGLISH, "%.2fTB", ret);
    }
    ret /= 1024;
    return String.format(Locale.ENGLISH, "%.2fPB", ret);
  }