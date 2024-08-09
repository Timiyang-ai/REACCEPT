public void setMode(short mode) {
    getOwningUserActions().updateByModeBits(Mode.extractOwnerBits(mode));
    getOwningGroupActions().updateByModeBits(Mode.extractGroupBits(mode));
    mOtherActions.updateByModeBits(Mode.extractOtherBits(mode));
  }