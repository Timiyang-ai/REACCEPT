public Fingerprint addBoolean(boolean input) {
    addBytes(new byte[] { (byte) (input ? 1 : 0) });
    return this;
  }