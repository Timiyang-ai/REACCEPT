static String generateFirstSegmentName(boolean isLogSegmented) {
    return isLogSegmented ? getName(0, 0) : "";
  }