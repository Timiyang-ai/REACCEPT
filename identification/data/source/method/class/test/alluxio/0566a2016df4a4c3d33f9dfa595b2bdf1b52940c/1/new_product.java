public short toShort() {
    int s = (mUserBits.ordinal() << 6) | (mGroupBits.ordinal() << 3) | mOtherBits.ordinal();
    return (short) s;
  }