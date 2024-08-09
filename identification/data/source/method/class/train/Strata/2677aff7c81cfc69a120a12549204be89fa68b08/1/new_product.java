public String summaryDescription() {
    return variant.getCode() + expiry.format(YM_FORMAT);
  }