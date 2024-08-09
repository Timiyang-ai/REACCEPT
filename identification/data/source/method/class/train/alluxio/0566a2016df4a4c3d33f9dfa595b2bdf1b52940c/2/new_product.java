public Mode applyUMask(Mode umask) {
    return new Mode(mOwnerBits.and(umask.mOwnerBits.not()), mGroupBits.and(umask.mGroupBits.not()),
        mOtherBits.and(umask.mOtherBits.not()));
  }