public static long parseSpaceSize(String spaceSize) {
    double alpha = 0.0001;
    String ori = spaceSize;
    String end = "";
    int tIndex = spaceSize.length() - 1;
    while (tIndex >= 0) {
      if (spaceSize.charAt(tIndex) > '9' || spaceSize.charAt(tIndex) < '0') {
        end = spaceSize.charAt(tIndex) + end;
      } else {
        break;
      }
      tIndex --;
    }
    spaceSize = spaceSize.substring(0, tIndex + 1);
    double ret = Double.parseDouble(spaceSize);
    end = end.toLowerCase();
    if (end.isEmpty() || end.equals("b")) {
      return (long) (ret + alpha);
    } else if (end.equals("kb")) {
      return (long) (ret * Constants.KB + alpha);
    } else if (end.equals("mb")) {
      return (long) (ret * Constants.MB + alpha);
    } else if (end.equals("gb")) {
      return (long) (ret * Constants.GB + alpha);
    } else if (end.equals("tb")) {
      return (long) (ret * Constants.TB + alpha);
    } else if (end.equals("pb")) {
      // When parsing petabyte values, we can't multiply with doubles and longs, since that will
      // lose presicion with such high numbers. Therefore we use a BigDecimal.
      BigDecimal pBDecimal = new BigDecimal(Constants.PB);
      return pBDecimal.multiply(BigDecimal.valueOf(ret)).longValue();
    } else {
      throw new IllegalArgumentException("Fail to parse " + ori + " to bytes");
    }
  }