public short getMode() {
    Mode.Bits owner = getOwningUserActions().toModeBits();
    Mode.Bits group = getOwningGroupActions().toModeBits();
    Mode.Bits other = mOtherActions.toModeBits();
    return new Mode(owner, group, other).toShort();
  }