public short toShort() {
    int s = (mUserMode.ordinal() << 6) | (mGroupMode.ordinal() << 3) | mOtherMode.ordinal();
    return (short) s;
  }