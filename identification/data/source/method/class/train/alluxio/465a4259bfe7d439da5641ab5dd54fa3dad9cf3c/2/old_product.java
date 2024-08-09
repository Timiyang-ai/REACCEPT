public static boolean equalIncreasingByteArray(int start, int len, byte[] arr) {
    if (arr == null) {
      return false;
    }
    for (int k = 0; k < len; k++) {
      if (arr[k] != (byte) (start + k)) {
        return false;
      }
    }
    return true;
  }