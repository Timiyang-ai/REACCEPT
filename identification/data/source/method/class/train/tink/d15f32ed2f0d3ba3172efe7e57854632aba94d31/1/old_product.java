static byte[] dbl(final byte[] value) {
    // Shift left by one.
    byte[] res = new byte[value.length];
    for (int i = 0; i < res.length; i++) {
      res[i] = (byte) (0xFE & (value[i] << 1));
      if (i < res.length - 1) {
        res[i] |= (byte) (0x01 & (value[i + 1] >> 7));
      }
    }
    res[15] ^= (byte) (0x87 & (value[0] >> 7));
    return res;
  }