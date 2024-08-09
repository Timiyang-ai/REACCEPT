public Fingerprint addBoolean(boolean input) {
    md5.update(input ? TRUE_BYTES : FALSE_BYTES);
    return this;
  }