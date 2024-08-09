static byte[] dbl(final byte[] value) {
    if (value.length != BLOCK_SIZE) {
      throw new IllegalArgumentException("value must be a block.");
    }

    // Note that >> is an arithmetical shift, which copies the leftmost bit to fill the
    // blanks created by shifting. For instance, x >> 7 will equal 0xFF if (x & 1), and 0x00
    // otherwise. This is a bit hard to read, but the operation is branchless, which is valuable
    // in this context.

    // Shift left by one.
    byte[] res = new byte[BLOCK_SIZE];
    for (int i = 0; i < BLOCK_SIZE; i++) {
      res[i] = (byte) (0xFE & (value[i] << 1));
      if (i < BLOCK_SIZE - 1) {
        res[i] |= (byte) (0x01 & (value[i + 1] >> 7));
      }
    }
    // And handle the modulus if needed (0x87 is the binary representation of the polynomial,
    // minus the x^128 part).
    res[BLOCK_SIZE - 1] ^= (byte) (0x87 & (value[0] >> 7));
    return res;
  }