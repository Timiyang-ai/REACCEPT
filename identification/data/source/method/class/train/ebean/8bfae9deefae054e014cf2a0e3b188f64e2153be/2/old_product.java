public static boolean sameByValue(Object v1, Object v2) {
    return v1 == null ? v2 == null : v1.equals(v2);
  }