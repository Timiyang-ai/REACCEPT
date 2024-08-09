static void makeVersionInfoBits(int version, BitArray bits) throws WriterException {
    bits.appendBits(version, 6);
    int bchCode = calculateBCHCode(version, VERSION_INFO_POLY);
    bits.appendBits(bchCode, 12);

    if (bits.getSize() != 18) {  // Just in case.
      throw new WriterException("should not happen but we got: " + bits.getSize());
    }
  }