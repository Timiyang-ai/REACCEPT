static byte[] time(final Performance p) {
    return DTDur.get(p.ns() / 1000000).string(null);
  }