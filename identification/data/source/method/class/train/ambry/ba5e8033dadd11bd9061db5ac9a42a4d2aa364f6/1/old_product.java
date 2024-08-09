static String generateFirstSegmentName(long numSegments) {
    if (numSegments <= 0) {
      throw new IllegalArgumentException("Number of segments <=0");
    }
    if (numSegments == 1) {
      return "";
    }
    return getName(0, 0);
  }