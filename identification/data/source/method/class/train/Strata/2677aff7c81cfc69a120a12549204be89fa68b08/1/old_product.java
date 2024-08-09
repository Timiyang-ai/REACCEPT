public String summaryDescription() {
    return expiry.format(YM_FORMAT) + variant.getCode();
  }