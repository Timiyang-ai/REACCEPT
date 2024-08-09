private Mode applyUMask(Mode umask) {
    mOwnerBits = mOwnerBits.and(umask.mOwnerBits.not());
    mGroupBits = mGroupBits.and(umask.mGroupBits.not());
    mOtherBits = mOtherBits.and(umask.mOtherBits.not());
    return this;
  }