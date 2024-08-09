public static boolean equalIncreasingByteArray(int value, int len, byte[] arr) {
    if (arr == null || arr.length != len) {
      return false;
    }
    for (int k = 0; k < len; k++) {
      if (arr[k] != (byte) (value + k)) {
        return false;
      }
    }
    return true;
  }