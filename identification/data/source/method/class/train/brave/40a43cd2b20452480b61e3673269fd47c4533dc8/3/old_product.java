public static long lowerHexToUnsignedLong(String lowerHex) {
    int length = lowerHex.length();
    if (length < 1 || length > 32) throw isntLowerHexLong(lowerHex);

    // trim off any high bits
    int beginIndex = length > 16 ? length - 16 : 0;

    return lowerHexToUnsignedLong(lowerHex, beginIndex);
  }