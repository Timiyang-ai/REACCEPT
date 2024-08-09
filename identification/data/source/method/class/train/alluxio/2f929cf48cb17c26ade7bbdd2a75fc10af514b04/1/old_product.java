public Mode applyUMask(Mode umask) {
    return new Mode(mUserMode.and(umask.mUserMode.not()), mGroupMode.and(umask.mGroupMode.not()),
        mOtherMode.and(umask.mOtherMode.not()));
  }