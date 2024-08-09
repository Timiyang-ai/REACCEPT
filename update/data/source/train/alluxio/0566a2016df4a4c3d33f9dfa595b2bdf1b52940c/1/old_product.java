public Mode applyUMask(Mode umask) {
    return new Mode(mUserBits.and(umask.mUserBits.not()), mGroupBits.and(umask.mGroupBits.not()),
        mOtherBits.and(umask.mOtherBits.not()));
  }