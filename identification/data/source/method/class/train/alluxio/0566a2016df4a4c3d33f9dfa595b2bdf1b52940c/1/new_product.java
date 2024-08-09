public short toShort() {
    int s = (mOwnerBits.ordinal() << 6) | (mGroupBits.ordinal() << 3) | mOtherBits.ordinal();
    return (short) s;
  }