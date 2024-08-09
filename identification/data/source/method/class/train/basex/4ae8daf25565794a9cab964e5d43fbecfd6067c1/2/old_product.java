static byte[] time(final Performance p) {
    return DTDur.get(p.time() / 1000000).string(null);
  }