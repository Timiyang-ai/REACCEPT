public static long lowerHexToUnsignedLong(CharSequence lowerHex, int index) {
    int endIndex = Math.min(index + 16, lowerHex.length());
    long result = lenientLowerHexToUnsignedLong(lowerHex, index, endIndex);
    if (result == 0) throw isntLowerHexLong(lowerHex);
    return result;
  }